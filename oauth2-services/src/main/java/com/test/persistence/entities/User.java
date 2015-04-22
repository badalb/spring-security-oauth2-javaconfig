package com.test.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.util.CommonConstant;

/**
 * The persistent class for the tbluser database table.
 * 
 */
@Entity
@Table(name = "tbluser", schema = CommonConstant.EMBRACE_SCHEMA_NAME)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hu_id_gen", strategy = "com.test.config.security.util.IdGenerator")
	@GeneratedValue(generator = "hu_id_gen")
	@Column(name = "user_id")
	private String userId;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "is_deleted")
	private String isDeleted;

	@Column(name = "is_inactive")
	private String isInactive;

	@Column(name = "is_locked")
	private String isLocked;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_date")
	private Date lastLoginDate;

	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_date")
	private Date lastModifiedDate;

	@Column(name = "last_name")
	private String lastName;

	private String password;

	@Column(name = "user_name")
	private String userName;

	@OneToMany(mappedBy = "tbluser", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<UserRole> tbluserRoles;

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsInactive() {
		return this.isInactive;
	}

	public void setIsInactive(String isInactive) {
		this.isInactive = isInactive;
	}

	public String getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserRole> getTbluserRoles() {
		return this.tbluserRoles;
	}

	public void setTbluserRoles(List<UserRole> tbluserRoles) {
		this.tbluserRoles = tbluserRoles;
	}

	public UserRole addTbluserRole(UserRole tbluserRole) {
		getTbluserRoles().add(tbluserRole);
		tbluserRole.setTbluser(this);

		return tbluserRole;
	}

	public UserRole removeTbluserRole(UserRole tbluserRole) {
		getTbluserRoles().remove(tbluserRole);
		tbluserRole.setTbluser(null);

		return tbluserRole;
	}


}