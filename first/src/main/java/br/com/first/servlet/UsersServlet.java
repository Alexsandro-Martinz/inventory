package br.com.first.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import br.com.first.dao.UserDao;
import br.com.first.forms.RegisterForm;
import br.com.first.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/main/users")
@MultipartConfig
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RegisterForm form = new RegisterForm();
		PrintWriter out = response.getWriter();

		form.setFname(request.getParameter("fname"));
		form.setLname(request.getParameter("lname"));
		form.setUsername(request.getParameter("username"));
		form.setEmail(request.getParameter("email"));
		form.setPassword(request.getParameter("password"));

		Part part = request.getPart("photo");
		byte[] photo = IOUtils.toByteArray(part.getInputStream());
		String photo64 = "data:" + part.getContentType() + ";base64," + Base64.getEncoder().encodeToString(photo);

		if (form.isValid()) {
			User user = form.getUser();
			user.setPhoto(photo64);
			user.setPhotoExtension(part.getContentType().split("\\/")[1]);
			Long id = new UserDao().insert(user);

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
