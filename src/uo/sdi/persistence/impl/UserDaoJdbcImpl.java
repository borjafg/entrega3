package uo.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.UserDao;
import uo.sdi.persistence.util.JdbcTemplate;
import uo.sdi.persistence.util.RowMapper;


public class UserDaoJdbcImpl implements UserDao
{
	public class UserMapper implements RowMapper<User>
	{
		@Override
		public User toObject(ResultSet rs) throws SQLException
		{
			User res = new User();
			
			res.setId(  		rs.getLong("id") );
			res.setLogin(  		rs.getString("login") );
			res.setPassword(  	rs.getString("password") );
			res.setName(  		rs.getString("name") );
			res.setSurname(  	rs.getString("surname") );
			res.setEmail(  		rs.getString("email") );
			res.setStatus(  	UserStatus.values()[ rs.getInt("status") ] );
			
			return res;
		}
	}
	
	private	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public Long save(User dto)
	{
		jdbcTemplate.execute("USER_INSERT", 
				dto.getLogin(), 
				dto.getPassword(), 
				dto.getName(),
				dto.getSurname(),
				dto.getEmail(),
				dto.getStatus().ordinal()  // enum saved as integer
			);
		
		return jdbcTemplate.getGeneratedKey();
	}
	
	@Override
	public int update(User dto)
	{
		return jdbcTemplate.execute("USER_UPDATE", 
				dto.getLogin(), 
				dto.getPassword(), 
				dto.getName(),
				dto.getSurname(),
				dto.getEmail(),
				dto.getStatus().ordinal(),
				dto.getId()
			);
	}
	
	@Override
	public int delete(Long id)
	{
		return jdbcTemplate.execute("USER_DELETE", id);
	}
	
	@Override
	public User findById(Long id)
	{
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_ID", 
				new UserMapper(), 
				id
			);
	}

	@Override
	public List<User> findAll()
	{
		return jdbcTemplate.queryForList("USER_FIND_ALL", new UserMapper());
	}
	
	@Override
	public User findByLogin(String login)
	{
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_LOGIN", 
				new UserMapper(), 
				login
			);
	}
}