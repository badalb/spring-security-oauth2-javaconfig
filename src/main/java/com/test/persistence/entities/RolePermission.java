package com.test.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.test.util.CommonConstant;


/**
 * The persistent class for the tblrole_permission database table.
 * 
 */
@Entity
@Table(name="tblrole_permission", schema = CommonConstant.EMBRACE_SCHEMA_NAME)
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePermissionPK id;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_deleted")
	private String isDeleted;

	@Column(name="is_inactive")
	private String isInactive;

	@Column(name="last_modified_by")
	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	//bi-directional many-to-one association to Tblpermission
	@ManyToOne
	@JoinColumn(name="permission_id", insertable=false, updatable=false)
	private Permission tblpermission;

	//bi-directional many-to-one association to Tblrole
	@ManyToOne
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	private Role tblrole;

	public RolePermission() {
	}

	public RolePermissionPK getId() {
		return this.id;
	}

	public void setId(RolePermissionPK id) {
		this.id = id;
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

	public Permission getTblpermission() {
		return this.tblpermission;
	}

	public void setTblpermission(Permission tblpermission) {
		this.tblpermission = tblpermission;
	}

	public Role getTblrole() {
		return this.tblrole;
	}

	public void setTblrole(Role tblrole) {
		this.tblrole = tblrole;
	}

}