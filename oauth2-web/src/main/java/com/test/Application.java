package com.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class Application extends SpringBootServletInitializer implements
		CommandLineRunner {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(Application.class).showBanner(false).run(
				args);
	}

	@Override
	protected final SpringApplicationBuilder configure(
			final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
