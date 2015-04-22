package com.test.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tblrole_permission database table.
 * 
 */
@Embeddable
public class RolePermissionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="role_permission_id")
	private String rolePermissionId;

	@Column(name="permission_id", insertable=false, updatable=false)
	private String permissionId;

	@Column(name="role_id", insertable=false, updatable=false)
	private String roleId;

	public RolePermissionPK() {
	}
	public String getRolePermissionId() {
		return this.rolePermissionId;
	}
	public void setRolePermissionId(String rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}
	public String getPermissionId() {
		return this.permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePermissionPK)) {
			return false;
		}
		RolePermissionPK castOther = (RolePermissionPK)other;
		return 
			this.rolePermissionId.equals(castOther.rolePermissionId)
			&& this.permissionId.equals(castOther.permissionId)
			&& this.roleId.equals(castOther.roleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rolePermissionId.hashCode();
		hash = hash * prime + this.permissionId.hashCode();
		hash = hash * prime + this.roleId.hashCode();
		
		return hash;
	}
}