package com.ctoutweb.dlc.model;

import java.util.Objects;

import com.ctoutweb.dlc.model.encryption.EncryptRandomWordResponse;
import com.ctoutweb.dlc.service.random.RandomCategory;

public class SaveEncryptedRandomWord {
	private EncryptRandomWordResponse encryptedRandomWord;
	private RandomCategory randomCategory;
	private int userId;

	private SaveEncryptedRandomWord(Builder builder) {
		this.encryptedRandomWord = builder.encryptedRandomWord;
		this.randomCategory = builder.randomCategory;
		this.userId = builder.userId;
	}
	/**
	 * @return the encryptedRandomWord
	 */
	public EncryptRandomWordResponse getEncryptedRandomWord() {
		return encryptedRandomWord;
	}
	/**
	 * @param encryptedRandomWord the encryptedRandomWord to set
	 */
	public void setEncryptedRandomWord(EncryptRandomWordResponse encryptedRandomWord) {
		this.encryptedRandomWord = encryptedRandomWord;
	}
	/**
	 * @return the randomCategory
	 */
	public RandomCategory getRandomCategory() {
		return randomCategory;
	}
	/**
	 * @param randomCategory the randomCategory to set
	 */
	public void setRandomCategory(RandomCategory randomCategory) {
		this.randomCategory = randomCategory;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(encryptedRandomWord, randomCategory, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveEncryptedRandomWord other = (SaveEncryptedRandomWord) obj;
		return Objects.equals(encryptedRandomWord, other.encryptedRandomWord) && randomCategory == other.randomCategory
				&& userId == other.userId;
	}
	@Override
	public String toString() {
		return "SaveEncryptedRandomWord [encryptedRandomWord=" + encryptedRandomWord + ", randomCategory="
				+ randomCategory + ", userId=" + userId + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private EncryptRandomWordResponse encryptedRandomWord;
		private RandomCategory randomCategory;
		private int userId;

		private Builder() {
		}

		public Builder withEncryptedRandomWord(EncryptRandomWordResponse encryptedRandomWord) {
			this.encryptedRandomWord = encryptedRandomWord;
			return this;
		}

		public Builder withRandomCategory(RandomCategory randomCategory) {
			this.randomCategory = randomCategory;
			return this;
		}

		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public SaveEncryptedRandomWord build() {
			return new SaveEncryptedRandomWord(this);
		}
	}
	
	
	
}
