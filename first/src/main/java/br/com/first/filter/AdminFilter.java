package br.com.first.filter;

import java.io.IOException;

import br.com.first.model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns = {"/main/adm/*", "/main/users/*", "/main/users"})
public class AdminFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		User user = (User) req.getSession().getAttribute("user");

		if (Boolean.FALSE.equals(user.isAdmin())) {
			resp.sendError(405);
			return;
		}

		chain.doFilter(request, response);
	}

}
