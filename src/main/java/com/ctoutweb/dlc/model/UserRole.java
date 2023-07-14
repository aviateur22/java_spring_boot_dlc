package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.Objects;

public class UserRole {
	private int id;
	private String name;
	private Date createdAt;
	
	private UserRole(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.createdAt = builder.createdAt;
		
	}
	
	public UserRole() {
		
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", name=" + name + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private String name;
		private Date createdAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public UserRole build() {
			return new UserRole(this);
		}
	}
	
	

}
