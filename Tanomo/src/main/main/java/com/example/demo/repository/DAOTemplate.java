package com.example.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOTemplate {

	protected Connection createConnection() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TANOMO", "user", "password");
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}

		return con;
	}
}
