package br.com.first.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.first.dao.UserDao;
import br.com.first.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main/users/*")
public class UsersIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Scanner scan = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
		if (scan.hasNext()) {
			String body = scan.useDelimiter("\\A").next();

			User user = new Gson().fromJson(body, User.class);
			new UserDao().update(user);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.parseLong(req.getPathInfo().replace("/", ""));
		new UserDao().delete(id);
	}
}
