package com.radostin.dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.radostin.model.User;

/**
 * This DAO (Data Access Layer) class provides CRUD (Create/Read/Update/Delete)
 * database operations for table users in the database.
 * 
 * @author Rado
 *
 */
public class UserDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public UserDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertUser(User user) throws SQLException {
		String sql = "INSERT INTO users (first_name, last_name, birth_date, phonenumber, email) VALUES (?, ?, ?, ?, ?)";
		connect();

		PreparedStatement statement = (PreparedStatement) jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setDate(3, (Date) user.getBirthdate());
		statement.setString(4, user.getPhoneNumber());
		statement.setString(5, user.getEmail());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<User> listAllUsers() throws SQLException {
		List<User> listUser = new ArrayList<>();

		String sql = "SELECT * FROM users";

		connect();

		Statement statement = (Statement) jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("iduser");
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			Date birthDate = resultSet.getDate("birth_date");
			String phoneNumber = resultSet.getString("phonenumber");
			String email = resultSet.getString("email");

			User user = new User(id, firstName, lastName, birthDate, phoneNumber, email);
			listUser.add(user);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listUser;
	}

	public boolean deleteUser(User user) throws SQLException {
		String sql = "DELETE FROM users where iduser = ?";

		connect();

		PreparedStatement statement = (PreparedStatement) jdbcConnection.prepareStatement(sql);
		statement.setInt(1, user.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		String sql = "UPDATE users SET first_name = ?, last_name = ?, birth_date = ?, phonenumber = ?, email = ?";
		sql += " WHERE iduser = ?";
		connect();

		PreparedStatement statement = (PreparedStatement) jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setDate(3, (Date) user.getBirthdate());
		statement.setString(4, user.getPhoneNumber());
		statement.setString(5, user.getEmail());
		statement.setInt(6, user.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public User getUser(int id) throws SQLException {
		User user = null;
		String sql = "SELECT * FROM users WHERE iduser = ?";

		connect();

		PreparedStatement statement = (PreparedStatement) jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			Date birthdate = resultSet.getDate("birth_date");
			String phoneNumber = resultSet.getString("phonenumber");
			String email = resultSet.getString("email");

			user = new User(id, firstName, lastName, birthdate, phoneNumber, email);
		}

		resultSet.close();
		statement.close();

		return user;
	}
}
