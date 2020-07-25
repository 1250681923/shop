package com.in.dao;

import java.sql.SQLException;

import com.in.domain.User;

public interface UserDao {

	void save(User user) throws Exception ;

	User getByCode(String code) throws Exception;
	
	User getByUsername(String username) throws Exception;

	void update(User user) throws Exception;

	User getByUsernameAndPwd(String username, String password) throws Exception;

}
