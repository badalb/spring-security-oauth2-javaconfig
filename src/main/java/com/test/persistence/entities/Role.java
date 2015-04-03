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

import com.test.util.CommonConstant;


/**
 * The persistent class for the tblrole database table.
 * 
 */
@Entity
@Table(name="tblrole", schema = CommonConstant.EMBRACE_SCHEMA_NAME)
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hu_id_gen", strategy = "com.test.config.security.util.IdGenerator")
	@GeneratedValue(generator = "hu_id_gen")
	@Column(name="role_id")
	private String roleId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String description;

	@Column(name="is_deleted")
	private String isDeleted;

	@Column(name="is_inactive")
	private String isInactive;

	@Column(name="last_modified_by")
	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	private String name;

	//bi-directional many-to-one association to TblrolePermission
	@OneToMany(mappedBy="tblrole", fetch=FetchType.EAGER)
	private List<RolePermission> tblrolePermissions;

	//bi-directional many-to-one association to TbluserRole
	@OneToMany(mappedBy="tblrole")
	private List<UserRole> tbluserRoles;

	public Role() {
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RolePermission> getTblrolePermissions() {
		return this.tblrolePermissions;
	}

	public void setTblrolePermissions(List<RolePermission> tblrolePermissions) {
		this.tblrolePermissions = tblrolePermissions;
	}

	public RolePermission addTblrolePermission(RolePermission tblrolePermission) {
		getTblrolePermissions().add(tblrolePermission);
		tblrolePermission.setTblrole(this);

		return tblrolePermission;
	}

	public RolePermission removeTblrolePermission(RolePermission tblrolePermission) {
		getTblrolePermissions().remove(tblrolePermission);
		tblrolePermission.setTblrole(null);

		return tblrolePermission;
	}

	public List<UserRole> getTbluserRoles() {
		return this.tbluserRoles;
	}

	public void setTbluserRoles(List<UserRole> tbluserRoles) {
		this.tbluserRoles = tbluserRoles;
	}

	public UserRole addTbluserRole(UserRole tbluserRole) {
		getTbluserRoles().add(tbluserRole);
		tbluserRole.setTblrole(this);

		return tbluserRole;
	}

	public UserRole removeTbluserRole(UserRole tbluserRole) {
		getTbluserRoles().remove(tbluserRole);
		tbluserRole.setTblrole(null);

		return tbluserRole;
	}

}