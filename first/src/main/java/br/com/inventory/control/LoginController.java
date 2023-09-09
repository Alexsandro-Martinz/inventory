package br.com.first.control;

public class LoginController {

	/**
	 * LoginController is the class using to handle login events
	 */

	public static boolean authenticate(String username, String password) {
		/**
		 * Authenticate on system with username and login
		 * 
		 * @param username : String
		 * @param password : String
		 * @return If the parameters match with data on the database return an object
		 *         instance if not return null
		 */

		if (username.equals("admin") && password.equals("admin")) {
			return true;
		}
		return false;
	}

}
