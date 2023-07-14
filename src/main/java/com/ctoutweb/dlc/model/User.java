package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ctoutweb.dlc.security.Role;

import java.util.Collections;

public class User {
	
	private int id;
	private String email;		
	private Date lastLoginAt;
	private List<Role> roles;
	private List<Friend> friends;
	private List<Product> products;
	private Date createdAt;
	private Date updatedAt;
	
	public User() {
		
	}

	private User(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.lastLoginAt = builder.lastLoginAt;
		this.roles = builder.roles;
		this.friends = builder.friends;
		this.products = builder.products;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the lastLoginAt
	 */
	public Date getLastLoginAt() {
		return lastLoginAt;
	}
	/**
	 * @param lastLoginAt the lastLoginAt to set
	 */
	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	/**
	 * @return the friends
	 */
	public List<Friend> getFriends() {
		return friends;
	}
	/**
	 * @param friends the friends to set
	 */
	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, email, friends, id, lastLoginAt, products, roles, updatedAt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email)
				&& Objects.equals(friends, other.friends) && id == other.id
				&& Objects.equals(lastLoginAt, other.lastLoginAt) && Objects.equals(products, other.products)
				&& Objects.equals(roles, other.roles) && Objects.equals(updatedAt, other.updatedAt);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", lastLoginAt=" + lastLoginAt + ", roles=" + roles
				+ ", friends=" + friends + ", products=" + products + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private String email;
		private Date lastLoginAt;
		private List<Role> roles = Collections.emptyList();
		private List<Friend> friends = Collections.emptyList();
		private List<Product> products = Collections.emptyList();
		private Date createdAt;
		private Date updatedAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withLastLoginAt(Date lastLoginAt) {
			this.lastLoginAt = lastLoginAt;
			return this;
		}

		public Builder withRoles(List<Role> roles) {
			this.roles = roles;
			return this;
		}

		public Builder withFriends(List<Friend> friends) {
			this.friends = friends;
			return this;
		}

		public Builder withProducts(List<Product> products) {
			this.products = products;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder withUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
