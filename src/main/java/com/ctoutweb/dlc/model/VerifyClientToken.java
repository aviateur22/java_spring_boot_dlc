package com.ctoutweb.dlc.model;

import java.util.List;
import java.util.Objects;

import com.ctoutweb.dlc.service.random.RandomTokenCategory;

import java.util.Collections;

public class VerifyClientToken {
	private boolean isBase64Url;
	private boolean isTokenEncrypted;
	private String clientToken;
	private List<RandomConfirmationToken> userRandomConfirmationTokens;
	private RandomConfirmationToken tokenFromDatabase;
	private String exceptionMessage;
	private RandomTokenCategory randomTokenCategory;
	
	private VerifyClientToken(Builder builder) {
		this.isBase64Url = builder.isBase64Url;
		this.isTokenEncrypted = builder.isTokenEncrypted;
		this.clientToken = builder.clientToken;
		this.userRandomConfirmationTokens = builder.userRandomConfirmationTokens;
		this.tokenFromDatabase = builder.tokenFromDatabase;
		this.exceptionMessage = builder.exceptionMessage;
		this.randomTokenCategory = builder.randomTokenCategory;
	}
	/**
	 * @return the isBase64Url
	 */
	public boolean getIsBase64Url() {
		return isBase64Url;
	}
	/**
	 * @param isBase64Url the isBase64Url to set
	 */
	public void setBase64Url(boolean isBase64Url) {
		this.isBase64Url = isBase64Url;
	}
	/**
	 * @return the isTokenEncrypted
	 */
	public boolean getIsTokenEncrypted() {
		return isTokenEncrypted;
	}
	/**
	 * @param isTokenEncrypted the isTokenEncrypted to set
	 */
	public void setTokenEncrypted(boolean isTokenEncrypted) {
		this.isTokenEncrypted = isTokenEncrypted;
	}
	/**
	 * @return the clientToken
	 */
	public String getClientToken() {
		return clientToken;
	}
	/**
	 * @param clientToken the clientToken to set
	 */
	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
	/**
	 * @return the userRandomConfirmationTokens
	 */
	public List<RandomConfirmationToken> getUserRandomConfirmationTokens() {
		return userRandomConfirmationTokens;
	}
	/**
	 * @param userRandomConfirmationTokens the userRandomConfirmationTokens to set
	 */
	public void setUserRandomConfirmationTokens(List<RandomConfirmationToken> userRandomConfirmationTokens) {
		this.userRandomConfirmationTokens = userRandomConfirmationTokens;
	}
	/**
	 * @return the token
	 */
	public RandomConfirmationToken getTokenFromDatabase() {
		return tokenFromDatabase;
	}
	/**
	 * @param token the token to set
	 */
	public void setTokenFromDatabase(RandomConfirmationToken tokenFromDatabase) {
		this.tokenFromDatabase = tokenFromDatabase;
	}
	/**
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	/**
	 * @param exceptionMessage the exceptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	/**
	 * @return the randomTokenCategory
	 */
	public RandomTokenCategory getRandomTokenCategory() {
		return randomTokenCategory;
	}
	/**
	 * @param randomTokenCategory the randomTokenCategory to set
	 */
	public void setRandomTokenCategory(RandomTokenCategory randomTokenCategory) {
		this.randomTokenCategory = randomTokenCategory;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clientToken, exceptionMessage, isBase64Url, isTokenEncrypted, randomTokenCategory, tokenFromDatabase,
				userRandomConfirmationTokens);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerifyClientToken other = (VerifyClientToken) obj;
		return Objects.equals(clientToken, other.clientToken)
				&& Objects.equals(exceptionMessage, other.exceptionMessage) && isBase64Url == other.isBase64Url
				&& isTokenEncrypted == other.isTokenEncrypted && randomTokenCategory == other.randomTokenCategory
				&& Objects.equals(tokenFromDatabase, other.tokenFromDatabase)
				&& Objects.equals(userRandomConfirmationTokens, other.userRandomConfirmationTokens);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private boolean isBase64Url;
		private boolean isTokenEncrypted;
		private String clientToken;
		private List<RandomConfirmationToken> userRandomConfirmationTokens = Collections.emptyList();
		private RandomConfirmationToken tokenFromDatabase;
		private String exceptionMessage;
		private RandomTokenCategory randomTokenCategory;

		private Builder() {
		}

		public Builder withIsBase64Url(boolean isBase64Url) {
			this.isBase64Url = isBase64Url;
			return this;
		}

		public Builder withIsTokenEncrypted(boolean isTokenEncrypted) {
			this.isTokenEncrypted = isTokenEncrypted;
			return this;
		}

		public Builder withClientToken(String clientToken) {
			this.clientToken = clientToken;
			return this;
		}

		public Builder withUserRandomConfirmationTokens(List<RandomConfirmationToken> userRandomConfirmationTokens) {
			this.userRandomConfirmationTokens = userRandomConfirmationTokens;
			return this;
		}

		public Builder withToken(RandomConfirmationToken tokenFromDatabase) {
			this.tokenFromDatabase = tokenFromDatabase;
			return this;
		}

		public Builder withExceptionMessage(String exceptionMessage) {
			this.exceptionMessage = exceptionMessage;
			return this;
		}

		public Builder withRandomTokenCategory(RandomTokenCategory randomTokenCategory) {
			this.randomTokenCategory = randomTokenCategory;
			return this;
		}

		public VerifyClientToken build() {
			return new VerifyClientToken(this);
		}
	}

	
}
