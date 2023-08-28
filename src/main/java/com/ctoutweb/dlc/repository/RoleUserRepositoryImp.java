package com.ctoutweb.dlc.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.RoleUserEntity;
import com.ctoutweb.dlc.model.UserRole;

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
		
		int insertRow = namedParameterJdbcTemplate.update(roleUserQuery, sqlParam, this.keyHolder);		
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new RuntimeException("erreur insertion roleUSer");			
		
		return this.getKeyHolderId();
	}


	@Override
	public List<UserRole> findUserRoleByUserId(int userId) {
		String query ="SELECT r_u.created_at, r.id, r.name "
				+ "FROM role_user AS r_u "
				+ "JOIN roles AS r "
				+ "ON r_u.role_id = r.id "
				+ "WHERE r_u.user_id = ?";
		
		return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(UserRole.class), userId);
	}

}
