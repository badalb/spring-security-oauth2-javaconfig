package com.test.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.persistence.entities.RolePermission;
import com.test.persistence.entities.User;
import com.test.persistence.entities.UserRole;

/**
 * Created by santoshm1 on 02/06/14.
 */
public class SecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -694318736321199793L;

	@JsonIgnore
	private User user;

	private Set<String> permissions;

	private Set<String> roles;

	public SecurityUser(User user) {

		if (user != null) {
			this.setUserId(user.getUserId());
			this.setUserName(user.getUserName());
			this.setFirstName(user.getFirstName());
			this.setLastName(user.getLastName());
			this.setPassword(user.getPassword());
			this.user = user;
		}
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<UserRole> userRoles = this.getUser().getTbluserRoles();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (userRoles != null) {
			for (UserRole role : userRoles) {

				if (this.getRoles() == null) {
					this.roles = new HashSet<String>();
				}

				roles.add(role.getTblrole().getName());

				if (this.getPermissions() == null) {
					this.permissions = new HashSet<String>();
				}

				for (RolePermission permission : role.getTblrole()
						.getTblrolePermissions()) {
					permissions.add(permission.getTblpermission().getKeyId());
				}
			}
		}

		if (permissions != null) {
			for (String permission : permissions) {
				GrantedAuthority authority = new SimpleGrantedAuthority(
						permission);
				authorities.add(authority);
			}
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getFirstName() + super.getLastName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
