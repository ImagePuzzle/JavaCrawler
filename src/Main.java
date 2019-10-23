import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.ibatis.jdbc.ScriptRunner;

import sql.MySQLConnector;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			if(new File("crawler.sql").exists()) {
		Connection conn = new MySQLConnector().mysqlConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("CREATE DATABASE crawler");
		ScriptRunner sr = new ScriptRunner(conn);
	    Reader reader = new BufferedReader(new FileReader("crawler.sql"));
	    sr.runScript(reader);
			}
		}catch(Exception exc)
		{
			System.out.println("Database already created!");
		}
	}

}
