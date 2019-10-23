package entity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import sql.MySQLConnector;

public class Page {
	
	private String link;

	public Page(String link)
	{
		this.link = link;
	}
	
	public boolean contains()
	{
		try {
			Connection conn = new MySQLConnector().mysqlConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT count(Link) FROM crawler WHERE Link = ?");
			stmt.setString(1, this.link);
			ResultSet rs = stmt.executeQuery();
			if(rs.getInt("count(Link)") == 0)
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void add()
	{
		try {
			Connection conn = new MySQLConnector().mysqlConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO crawler (Time, Link, Data) values (?, ?, ?)");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			stmt.setLong(1, timestamp.getTime());
			stmt.setString(2, this.link);
			stmt.setString(3, getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getContent() throws IOException
	{
		Document doc = Jsoup.connect(this.link)
                .userAgent("Mozilla/5.0")
                .timeout(30000)
                .get();
		return doc.text();
	}
	
	public void replace()
	{
		try {
			Connection conn = new MySQLConnector().mysqlConnection();
			PreparedStatement stmt = conn.prepareStatement("UPDATE TABLE crawler set (Time, Link, Data) values (?, ?, ?)");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			stmt.setLong(1, timestamp.getTime());
			stmt.setString(2, this.link);
			stmt.setString(3, getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
