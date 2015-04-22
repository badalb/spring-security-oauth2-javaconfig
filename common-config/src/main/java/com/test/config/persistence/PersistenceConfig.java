package com.test.config.persistence;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "emFactory", transactionManagerRef = "transactionManager", basePackages = { "com.test.persistence.repositories" })
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Primary
	@Profile("dev")
	@Bean(name = "dataSource")
	public DataSource dataSourceDev() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env
				.getProperty("datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("datasource.url"));
		dataSource.setUsername(env.getProperty("datasource.username"));
		dataSource.setPassword(env.getProperty("datasource.password"));

		return dataSource;
	}

	@Primary
	@Bean(name = "dataSource")
	@Profile("production")
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource("java:comp/env/jdbc/mls");
	}

	@Bean(name = "flyway")
	@Profile("dev")
	public Flyway getFlywayDev() {
		org.flywaydb.core.Flyway flyway = new Flyway();
		flyway.setDataSource(dataSourceDev());
		flyway.setInitOnMigrate(true);
		flyway.migrate();
		return flyway;
	}

	@Bean(name = "flyway")
	@Profile("production")
	public Flyway getFlyway() {
		org.flywaydb.core.Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource());
		flyway.setInitOnMigrate(true);
		flyway.migrate();
		return flyway;
	}

	@Bean(name = "entityManager")
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	@Primary
	@Bean(name = "emFactory")
	@DependsOn("flyway")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.FALSE);
		vendorAdapter.setShowSql(Boolean.FALSE);
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.test.persistence.entities");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect",
				env.getProperty("spring.jpa.properties.hibernate.dialect"));
		factory.setJpaProperties(jpaProperties);
		factory.setPersistenceUnitName("persistenceUnit");
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory.getObject();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory factory = entityManagerFactory();
		return new JpaTransactionManager(factory);
	}

}
