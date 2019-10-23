package sql;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MySQLConnector {

	private MysqlDataSource dataSource;
	
	public MySQLConnector() {
	dataSource = new MysqlDataSource();
	dataSource.setUser("root");
	dataSource.setPassword("");
	dataSource.setServerName("127.0.0.1");
	dataSource.setDatabaseName("crawler");
	}
	
	public Connection mysqlConnection() throws SQLException
	{
		return dataSource.getConnection();
		
	}
	
}
