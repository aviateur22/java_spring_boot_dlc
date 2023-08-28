package com.ctoutweb.dlc.model.encryption;

import java.util.Objects;

public class EncryptRandomWordResponse {
	private String encryptRandomWord;
	private String ivString;
	
	private EncryptRandomWordResponse(Builder builder) {
		this.encryptRandomWord = builder.encryptRandomWord;
		this.ivString = builder.ivString;
	}
	/**
	 * @return the encryptRandomWord
	 */
	public String getEncryptRandomWord() {
		return encryptRandomWord;
	}
	/**
	 * @param encryptRandomWord the encryptRandomWord to set
	 */
	public void setEncryptRandomWord(String encryptRandomWord) {
		this.encryptRandomWord = encryptRandomWord;
	}
	/**
	 * @return the ivString
	 */
	public String getIvString() {
		return ivString;
	}
	/**
	 * @param ivString the ivString to set
	 */
	public void setIvString(String ivString) {
		this.ivString = ivString;
	}
	@Override
	public int hashCode() {
		return Objects.hash(encryptRandomWord, ivString);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncryptRandomWordResponse other = (EncryptRandomWordResponse) obj;
		return Objects.equals(encryptRandomWord, other.encryptRandomWord) && Objects.equals(ivString, other.ivString);
	}
	@Override
	public String toString() {
		return "EncryptRandomWordResponse [encryptRandomWord=" + encryptRandomWord + ", ivString=" + ivString + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private String encryptRandomWord;
		private String ivString;

		private Builder() {
		}

		public Builder withEncryptRandomWord(String encryptRandomWord) {
			this.encryptRandomWord = encryptRandomWord;
			return this;
		}

		public Builder withIvString(String ivString) {
			this.ivString = ivString;
			return this;
		}

		public EncryptRandomWordResponse build() {
			return new EncryptRandomWordResponse(this);
		}
	}	
	
}
