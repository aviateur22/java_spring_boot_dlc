package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.Objects;

public class TokenIssue {
	private String token;
	private Date expiredAt;

	private TokenIssue(Builder builder) {
		this.token = builder.token;
		this.expiredAt = builder.expiredAt;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the expiredAt
	 */
	public Date getExpiredAt() {
		return expiredAt;
	}
	/**
	 * @param expiredAt the expiredAt to set
	 */
	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(token);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenIssue other = (TokenIssue) obj;
		return Objects.equals(token, other.token);
	}
	@Override
	public String toString() {
		return "TokenIssue [token=" + token + ", expiredAt=" + expiredAt + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String token;
		private Date expiredAt;

		private Builder() {
		}

		public Builder withToken(String token) {
			this.token = token;
			return this;
		}

		public Builder withExpiredAt(Date expiredAt) {
			this.expiredAt = expiredAt;
			return this;
		}

		public TokenIssue build() {
			return new TokenIssue(this);
		}
	}
	
	
}
