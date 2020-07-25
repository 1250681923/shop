package com.in.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.in.dao.UserDao;
import com.in.domain.User;
import com.in.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{
	
	/*
	 * Enregistrer l'utilisateur
	 */
	public void save(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		/*
		 *  `uid` varchar(32) NOT NULL,
		  `username` varchar(20) DEFAULT NULL,
		  `password` varchar(20) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  `email` varchar(30) DEFAULT NULL,
		  `telephone` varchar(20) DEFAULT NULL,
		  `birthday` date DEFAULT NULL,
		  `sex` varchar(10) DEFAULT NULL,
		  `state` int(11) DEFAULT NULL,
		  `code` varchar(64) DEFAULT NULL, 
		 */
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?);";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
	}

	@Override
	/*
	 * Trouver user par code d'activiation ou username
	 */
	public User getByCode(String code) throws Exception {
	    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	    String sql = "select * from user where code = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), code);
	}
	
	public User getByUsername(String username) throws Exception {
	    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	    String sql = "select * from user where username = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username);
	}

	@Override
	/*
	 * Mettre à jour l'utilisateur
	 */
	public void update(User user) throws Exception {
		 QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		 /*
			 *  `uid` varchar(32) NOT NULL,
			  `username` varchar(20) DEFAULT NULL,
			  `password` varchar(20) DEFAULT NULL,
			  `name` varchar(20) DEFAULT NULL,
			  `email` varchar(30) DEFAULT NULL,
			  `telephone` varchar(20) DEFAULT NULL,
			  `birthday` date DEFAULT NULL,
			  `sex` varchar(10) DEFAULT NULL,
			  `state` int(11) DEFAULT NULL,
			  `code` varchar(64) DEFAULT NULL, 
			 */
		 String sql = "update user set password = ?, state = ?, code = ? where uid = ? ";
		 qr.update(sql, user.getPassword(), user.getState(), user.getCode(), user.getUid());
		
	}

	/*
	 * Login
	 */
	public User getByUsernameAndPwd(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1";
		
		return qr.query(sql, new BeanHandler<>(User.class),username,password);
	}

}
