package br.com.first.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.first.connection.SingleConnection;
import br.com.first.forms.LoginModel;
import br.com.first.model.User;

public class UserDao {

	private Connection connection;

	public UserDao() {
		connection = SingleConnection.getConnection();
	}

	public void update(User user) {
		String sql = "UPDATE public.users SET";

		String firstName = user.getFirstName();
		if (firstName != null && !firstName.isEmpty()) {
			sql += " first_name='" + firstName + "',";
		}

		String lastName = user.getLastName();
		if (lastName != null && !lastName.isEmpty()) {
			sql += " last_name='" + lastName + "',";
		}

		String username = user.getUsername();
		if (username != null && !username.isEmpty()) {
			sql += " username='" + username + "',";
		}

		String email = user.getEmail();
		if (email != null && !email.isEmpty()) {
			sql += " email='" + email + "',";
		}

		String photo = user.getPhoto();
		if (photo != null && !photo.isEmpty()) {
			sql += " email='" + photo + "',";
		}

		String photoExtension = user.getPhotoExtension();
		if (photoExtension != null && !photoExtension.isEmpty()) {
			sql += " email='" + photoExtension + "',";
		}

		sql = sql.substring(0, sql.length() - 1);

		sql += " WHERE id=" + user.getId() + " and is_admin is false";

		try {
			connection.prepareStatement(sql).execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void delete(Long id) {
		String sql = "DELETE FROM public.users WHERE id=" + id + " and is_admin is false";
		try {
			connection.prepareStatement(sql).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User authenticate(LoginModel loginModel) {

		String sql = "SELECT * FROM users WHERE username= LOWER(?) and passwd = LOWER(?)";

		User user = null;

		try {

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, loginModel.getUsername());
			stm.setString(2, loginModel.getPassword());

			ResultSet result = stm.executeQuery();
			if (result.next()) {
				user = new User();
				user.setId(result.getLong("id"));
				user.setEmail(result.getString("email"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUsername(result.getString("username"));
				user.setAdmin(result.getBoolean("is_admin"));
				user.setPhoto(result.getString("photo"));
				user.setPhotoExtension(result.getString("photo_extension"));

			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return user;
	}

	public Long insert(User user) {
		Long id = null;
		String sql = "INSERT INTO users(username, passwd, first_name, last_name, email, photo, photo_extension)"
				+ " VALUES (LOWER(?), LOWER(?), LOWER(?), LOWER(?), LOWER(?), ?, ?)"
				+ " ON CONFLICT (username) DO NOTHING" + " RETURNING id";

		try (PreparedStatement stm = connection.prepareStatement(sql)) {

			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getFirstName());
			stm.setString(4, user.getLastName());
			stm.setString(5, user.getEmail());
			stm.setString(6, user.getPhoto());
			stm.setString(7, user.getPhotoExtension());

			ResultSet result = stm.executeQuery();

			if (result.next()) {
				id = result.getLong(1);
				connection.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return id;
	}

	public List<User> userList() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE is_admin is false";

		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			ResultSet result = stm.executeQuery();

			while (result.next()) {
				User user = new User();

				user.setId(result.getLong("id"));
				user.setEmail(result.getString("email"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUsername(result.getString("username"));
				user.setPhoto(result.getString("photo"));
				user.setPhotoExtension(result.getString("photo_extension"));

				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	public List<User> searchByUsername(String firstName) {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE first_name like '%" + firstName + "%' and is_admin is false";

		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			ResultSet result = stm.executeQuery();

			while (result.next()) {
				User user = new User();

				user.setId(result.getLong("id"));
				user.setEmail(result.getString("email"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUsername(result.getString("username"));

				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users; 

	}
}