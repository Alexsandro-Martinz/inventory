package br.com.first.servlet;

import java.io.IOException;

import br.com.first.dao.UserDao;
import br.com.first.forms.LoginModel;
import br.com.first.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/login", "/main/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			LoginModel login = new LoginModel();
			login.setUsername(request.getParameter("username"));
			login.setPassword(request.getParameter("password"));

			if (!login.isValid()) {
				request.setAttribute("message", "Enter a valid credentials.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}

			UserDao userDao = new UserDao();
			User user = userDao.authenticate(login);

			if (user != null) {

				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				String url = request.getParameter("url");

				url = url.equalsIgnoreCase("") ? "/main/home.jsp" : url;

				request.getRequestDispatcher(url).forward(request, response);
			} else {
				request.setAttribute("message", "Credentials not founded.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
