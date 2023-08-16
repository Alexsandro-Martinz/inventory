package br.com.first.forms;

import br.com.first.model.User;

public class RegisterForm {
	private String lname;
	private String fname;
	private String email;
	private String username;
	private String password;

	public boolean isValid() {

		if (username == null || username.isEmpty()) {
			return false;
		}
		
		if (password == null || password.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public User getUser() {
		User user = new User();
		
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}

	@Override
	public String toString() {
		return "RegisterForm [lname=" + lname + ", fname=" + fname + ", email=" + email + ", username=" + username
				+ "]";
	}

}
