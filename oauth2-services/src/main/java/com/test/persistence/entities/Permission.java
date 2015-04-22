package com.test.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.test.util.CommonConstant;


/**
 * The persistent class for the tblpermission database table.
 * 
 */
@Entity
@Table(name="tblpermission", schema = CommonConstant.EMBRACE_SCHEMA_NAME)
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hu_id_gen", strategy = "com.test.config.security.util.IdGenerator")
	@GeneratedValue(generator = "hu_id_gen")
	@Column(name="permission_id")
	private String permissionId;

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

	@Column(name="key_id")
	private String keyId;

	@Column(name="last_modified_by")
	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	private String name;

	//bi-directional many-to-one association to TblrolePermission
	@OneToMany(mappedBy="tblpermission")
	private List<RolePermission> tblrolePermissions;

	public Permission() {
	}

	public String getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
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

	public String getKeyId() {
		return this.keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
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
		tblrolePermission.setTblpermission(this);

		return tblrolePermission;
	}

	public RolePermission removeTblrolePermission(RolePermission tblrolePermission) {
		getTblrolePermissions().remove(tblrolePermission);
		tblrolePermission.setTblpermission(null);

		return tblrolePermission;
	}

}