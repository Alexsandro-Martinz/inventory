package br.com.first.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import br.com.first.dao.UserDao;
import br.com.first.forms.RegisterForm;
import br.com.first.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		Gson gson = new Gson();
		PrintWriter out = resp.getWriter();

		if (firstName != null && !firstName.isEmpty()) {
			out.write(gson.toJson(new UserDao().searchByUsername(firstName)));
		} else {
			out.write(gson.toJson(new UserDao().userList()));
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RegisterForm form = new RegisterForm();

		form.setFname(request.getParameter("fname"));
		form.setLname(request.getParameter("lname"));
		form.setUsername(request.getParameter("username"));
		form.setEmail(request.getParameter("email"));
		form.setPassword(request.getParameter("password"));

		PrintWriter out = response.getWriter();

		if (form.isValid()) {

			Long id = new UserDao().insert(form.getUser());

			if (id != null) {
				out.write("User saved with success.");
			} else {
				response.setStatus(400);
				out.write("User already exists.");
			}

		} else {
			response.setStatus(400);
			out.write("Enter a valid data.");
		}

	}

}
