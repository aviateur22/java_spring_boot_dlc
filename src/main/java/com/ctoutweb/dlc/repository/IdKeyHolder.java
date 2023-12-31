package com.ctoutweb.dlc.repository;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public abstract class IdKeyHolder {
	
	protected KeyHolder keyHolder;
	
	public IdKeyHolder() {
		this.keyHolder = new GeneratedKeyHolder();
	}

	
	@SuppressWarnings("null")
	public boolean isKeyHolderOrInsertRowUnvalid(int insertRow) {	
		return insertRow == 0 || keyHolder.getKeys().size() == 0;
	}
	
	@SuppressWarnings("null")
	public int getKeyHolderId() {
		return Integer.valueOf(keyHolder.getKeys().get("id").toString());
	}
	
}
