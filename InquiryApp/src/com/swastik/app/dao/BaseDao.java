package com.swastik.app.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class BaseDao {
	@Resource(name = "jdbc/EPS_DB")
	private DataSource dataSource;
	
	public Connection getDataBaseConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
