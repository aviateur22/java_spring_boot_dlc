package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import java.util.Collections;

public class User {	
	private int id;
	private String email;
	private boolean isAccountCreated;
	private Account account;
	private List<UserRole> roles;
	private List<Friend> friends;
	private List<Product> products;

	private User(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.isAccountCreated = builder.isAccountCreated;
		this.account = builder.account;
		this.roles = builder.roles;
		this.friends = builder.friends;
		this.products = builder.products;
	}	
	
	public User() {
		
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
	 * @return the isAccountCreated
	 */
	public boolean getIsAccountCreated() {
		return isAccountCreated;
	}

	/**
	 * @param isAccountCreated the isAccountCreated to set
	 */
	public void setAccountCreated(boolean isAccountCreated) {
		this.isAccountCreated = isAccountCreated;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the roles
	 */
	public List<UserRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<UserRole> roles) {
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

	@Override
	public int hashCode() {
		return Objects.hash(account, email, friends, id, isAccountCreated, products, roles);
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
		return Objects.equals(account, other.account) && Objects.equals(email, other.email)
				&& Objects.equals(friends, other.friends) && id == other.id
				&& isAccountCreated == other.isAccountCreated && Objects.equals(products, other.products)
				&& Objects.equals(roles, other.roles);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", isAccountCreated=" + isAccountCreated + ", account=" + account
				+ ", roles=" + roles + ", friends=" + friends + ", products=" + products + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private String email;
		private boolean isAccountCreated;
		private Account account;
		private List<UserRole> roles = Collections.emptyList();
		private List<Friend> friends = Collections.emptyList();
		private List<Product> products = Collections.emptyList();

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

		public Builder withIsAccountCreated(boolean isAccountCreated) {
			this.isAccountCreated = isAccountCreated;
			return this;
		}

		public Builder withAccount(Account account) {
			this.account = account;
			return this;
		}

		public Builder withRoles(List<UserRole> roles) {
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

		public User build() {
			return new User(this);
		}
	}
	
	
	
}
