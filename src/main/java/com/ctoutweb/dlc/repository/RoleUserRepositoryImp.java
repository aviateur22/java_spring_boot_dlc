package com.ctoutweb.dlc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.RoleUserEntity;

@Repository
public class RoleUserRepositoryImp extends IdKeyHolder implements RoleUserRepository {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final JdbcTemplate jdbcTemplate;

	public RoleUserRepositoryImp(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	@Transactional
	public int saveRoleUser(RoleUserEntity roleUser) {
		String roleUserQuery = "INSERT INTO role_user (user_id, role_id) VALUES (:userId, :roleId)";
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(roleUser);
		System.out.println(roleUser);
		int insertRow = namedParameterJdbcTemplate.update(roleUserQuery, sqlParam, this.keyHolder);
		System.out.println(this.getKeyHolderId());
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new RuntimeException("erreur insertion roleUSer");			
		
		return this.getKeyHolderId();
	}

}
