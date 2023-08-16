<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<header class="header">
	<div class="header-left">
		<div>
			<img alt=""
				src="<%=request.getContextPath()%>/main/assets/icons/menu-burger.png">
		</div>
	</div>

	<div class="header-center">
		<div>
			<a id="home.jsp" href="<%=request.getContextPath()%>/main/home.jsp">
				Home</a>
		</div>

		<c:if test="${user.admin}">
			
			<div>
				<a id="users-view.jsp"
					href="<%=request.getContextPath()%>/main/adm/users-view.jsp">
					Users</a>
			</div>

			<div>
				<a id="register-user.jsp"
					href="<%=request.getContextPath()%>/main/adm/register-user.jsp">
					Register User</a>
			</div>
		</c:if>
		<div></div>
	</div>

	<div class="header-right">
		<div class="header-username">${user.username}</div>
		<div class="logout-icon-container">
			<a href="<%=request.getContextPath()%>/logout"> <img alt=""
				src="<%=request.getContextPath()%>/main/assets/icons/logout.png">
			</a>
			<div class="logout-tooltip">logout</div>
		</div>
	</div>
	<script type="text/javascript">
	(function() {
		
		let path = '<%=request.getServletPath()%>
		';
			let current = path.split("/").pop();
			let element = document.getElementById(current);
			element.style = "text-shadow: 1px 1px 2px black; color: white;";
		})();
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</header>