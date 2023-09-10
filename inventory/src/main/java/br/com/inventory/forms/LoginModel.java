package br.com.inventory.forms;

public class LoginModel {
	private String username;
	private String password;
	private Boolean admin;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public boolean isValid() {

		if (username == null)
			return false;
		if (username.isEmpty())
			return false;
		if (password == null)
			return false;
		if (password.isEmpty())
			return false;

		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}
}
