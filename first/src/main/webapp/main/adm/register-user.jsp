<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First | Register User</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/general.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/header.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/register-user.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/general-form.css">
</head>
<body>

	<jsp:include page="/main/assets/jsp/header.jsp"></jsp:include>

	<main class="container-form">
		<form class="form" id="register-form" method="POST">
			<h1 class="form-title">Register User</h1>
			<div>
				<label for="fname">First Name</label> <input type="text" id="fname"
					name="fname" placeholder="First Name" autocomplete="off"
					 required="required">
			</div>
			<div>
				<label for="lname">Last Name</label> <input type="text" id="lname"
					name="lname" placeholder="Last Name" autocomplete="off"
					 required="required">
			</div>

			<div>
				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Username"
					autocomplete="off" required="required">
			</div>
			<div>
				<label for="email">Email</label> <input type="email" id="email"
					name="email" placeholder="Enter your email" autocomplete="off"
					 required="required">
			</div>
			<div>
				<label for="password1">Password</label> <input type="password"
					id="password1" name="password" placeholder="Password"
					autocomplete="off" required="required">
			</div>

			<div>
				<label for="password2">Confirm</label> <input type="password"
					id="password2" name="password2" placeholder="Confirm"
					autocomplete="off" required="required">
			</div>
			<div class="save-btn-container">
				<button class="submit-btn save-btn" type="button"
					onclick="submitForm()">Save</button>
			</div>
			<div class="msg" id="msg"></div>
		</form>
	</main>

	<script type="text/javascript">
	
		var msg = document.getElementById('msg');
		
		var fname = document.getElementById('fname');
		var lname = document.getElementById('lname');
		var email = document.getElementById('email');
		var username = document.getElementById('username');
		var password = document.getElementById('password1');
		var password2 = document.getElementById('password2');
		
		function setMsg(message) {
			msg.innerHTML = message;
			setInterval(() => {
				msg.innerHTML = "";
			}, 3000);
		}
	
		function cleanForm(){
			fname.value = "";
			lname.value = "";
			email.value = "";
			username.value = "";
			password.value = "";
			password2.value = "";
		}
		
		
		async function submitForm(){

			if(username.value == ""){
				alert("Please enter the username.");
				return;
			}
			
			if(password.value == "" || password2.value == ""){
				alert("Enter the password and confirm.");
				return;
			}
			
			if(password.value != password2.value){
				alert("Passoword must be iguals.");
				return;
			}
			
			$.ajax({
				method: "post",
				url : "<%=request.getContextPath()%>/main/users",
				data : {
					'fname': fname.value,
					'lname': lname.value,
					'email': email.value,
					'username': username.value,
					'password': password.value
				},
				success : (response) => {
					setMsg(response);
					cleanForm();	
				},
				
			}).fail(function(xhr, status, errorThrown){
				alert(xhr.responseText);
			});
		
		}
	</script>
</body>
</html>