<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
* {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
}

body {
	display: flex;
	justify-content: center;
	background: white;
}

.container {
	background: linear-gradient(120deg, rgba(34, 193, 195, 1) 0%,
		rgba(253, 187, 45, 1) 100%);
	display: flex;
	width: 240px;
	max-width: fit-content;
	align-items: center;
	justify-content: top;
	margin-top: 100px;
	flex-direction: column;
	border-radius: 10px;
	border: none;
	box-shadow: 1px 1px 5px lightgrey;
	padding: 30px;
	padding-bottom: 30px;
	height: 200px;
}

.form-login {
	display: flex;
	flex-direction: column;
	font-family: Arial, Helvetica, sans-serif;
}

.input-username, .input-password {
	font-size: 18px;
	padding: 2px;
	border-radius: 5px;
	border: solid 1px lightgray;
	outline: solid 1px gray;
}

.title {
	padding-bottom: 15px;
}

.input-username, .input-password {
	margin-bottom: 10px;
}

.submit-btn {
	font-size: 18px;
	font-weight: bold;
	color: white;
	background-color: cornflowerblue;
	border: none;
	border-radius: 5px;
	padding: 5px 15px;
	margin-top: 10px;
}

.message {
	display: flex;
	padding: 10px;
	justify-content: center;
	align-item: center;
	color: black;
}

.msg {
	padding: 2px;
	display: flex;
	justify-content: center;
	align-item: center;
}

.msg-close-btn {
	padding: 2px 8px;
	font-weight: bold;
	border-radius: 20px;
	font-size: 14px;
	border: none;
	margin-left: 10px;
	color: red;
	box-shadow: 1px 1px 4px gray;
	cursor: pointer;
}

.msg-close-btn:hover {
	opacity: 0.8;
}

.msg-close-btn:active {
	box-shadow: none;
}
</style>
</head>

<body>

	<main class="container">
		<h1 class="title">Login</h1>
		<form class="form-login" method="post" action="/first/login">
			<input type="hidden" name="url" id="url" value="${url}">
			<input class="input-username" name="username" id="username"
				type="text" placeholder="Username" required="required"> <input
				class="input-password" name="password" id="password" type="password"
				placeholder="password" required="required">
			<button class="submit-btn" type="submit">Sign In</button>
		</form>
		<% if (request.getAttribute("message") != null ) {%>
		<div class="message" id="message">
			<span class="msg"> ${message}</span>
			<button class="msg-close-btn" onclick="this.parentElement.remove()">x</button>
		</div>
		<%} %>
		
	</main>
</body>

</html>