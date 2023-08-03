package com.ctoutweb.dlc.model;

import java.util.Objects;

import com.ctoutweb.dlc.model.encryption.EncryptRandomWordResponse;
import com.ctoutweb.dlc.service.random.RandomTokenCategory;

public class SaveEncryptedRandomToken {
	private EncryptRandomWordResponse encryptedRandomWord;
	private RandomTokenCategory randomTokenCategory;
	private int userId;

	private SaveEncryptedRandomToken(Builder builder) {
		this.encryptedRandomWord = builder.encryptedRandomWord;
		this.randomTokenCategory = builder.randomTokenCategory;
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
		return Objects.hash(encryptedRandomWord, randomTokenCategory, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveEncryptedRandomToken other = (SaveEncryptedRandomToken) obj;
		return Objects.equals(encryptedRandomWord, other.encryptedRandomWord)
				&& randomTokenCategory == other.randomTokenCategory && userId == other.userId;
	}
	@Override
	public String toString() {
		return "SaveEncryptedRandomWord [encryptedRandomWord=" + encryptedRandomWord + ", randomTokenCategory="
				+ randomTokenCategory + ", userId=" + userId + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private EncryptRandomWordResponse encryptedRandomWord;
		private RandomTokenCategory randomTokenCategory;
		private int userId;

		private Builder() {
		}

		public Builder withEncryptedRandomWord(EncryptRandomWordResponse encryptedRandomWord) {
			this.encryptedRandomWord = encryptedRandomWord;
			return this;
		}

		public Builder withRandomTokenCategory(RandomTokenCategory randomTokenCategory) {
			this.randomTokenCategory = randomTokenCategory;
			return this;
		}

		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public SaveEncryptedRandomToken build() {
			return new SaveEncryptedRandomToken(this);
		}
	}

	
}
