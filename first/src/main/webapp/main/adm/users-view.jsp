<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First | Users View</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/general.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/general-form.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/header.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/users-view.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/users-view-modal.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/users-view-footer.css">
</head>
<body>

	<jsp:include page="/main/assets/jsp/header.jsp"></jsp:include>

	<main class="container">
		<h3 class="title">Users</h3>

		<table class="users" id="users"></table>
	</main>

	<div class="modal" id="modal">
		<div class="container-form">
			<form class="form" id="register-form">
				<h1 class="form-title">Update User</h1>
				<div>
					<label for="id">Id</label> <input type="text" id="id" name="id"
						readonly="readonly">
				</div>
				<div>
					<label for="fname">First Name</label> <input type="text" id="fname"
						name="fname" autocomplete="off" required="required">
				</div>
				<div>
					<label for="lname">Last Name</label> <input type="text" id="lname"
						name="lname" autocomplete="off" required="required">
				</div>

				<div>
					<label for="username">Username</label> <input type="text"
						id="username" name="username" autocomplete="off"
						required="required">
				</div>
				<div>
					<label for="email">Email</label> <input type="email" id="email"
						name="email" autocomplete="off" required="required">
				</div>

				<div class="save-btn-container">
					<div>
						<button class="submit-btn save-btn" type="button"
							onclick="updateUser()">Save</button>
					</div>
					<div>
						<button class="submit-btn save-btn" type="button"
							onclick="closeModal()">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<footer class="footer-container">
		<div>
			<input class="input-search" id="input-search-firstName"
				placeholder="Seach by First Name">
		</div>
		<div>
			<button class="btn-search" onclick="loadUsersFromSearch()">
				<img
					src="<%=request.getContextPath()%>/main/assets/icons/search.png">
			</button>
		</div>

	</footer>
	<script type="text/javascript">

var usersTable = document.getElementById("users");

var modal = document.getElementById("modal");

var id = document.getElementById("id");
var username = document.getElementById("username");
var fname = document.getElementById("fname");
var lname = document.getElementById("lname");
var email = document.getElementById("email");

async function updateUser(){
	
	const url = "<%=request.getContextPath()%>/main/users/" + id.value;
	
	var user = {};
	
	if(fname.value != ""){
		user.firstName = fname.value;
	}
	
	if(lname.value != ""){
		user.lastName = lname.value;
	}
	
	if(username.value != ""){
		user.username = username.value;
	}
	
	if(email.value != ""){
		user.email = email.value;
	}
	
	var counter = 0;
	for( var key in user){
		counter++;
	}
	
	if(counter > 0){
		user.id = id.value;
	} else {
		alert("Change a field to update.");
		return;
	}
	
	
	$.ajax({
		url: url,
		type: 'PUT',
		contentType: 'application/json',
		data : JSON.stringify(user),
		success : (response) => alert(user.username + " updated."),
		
	}).fail(function(xhr, status, errorThrown){
		alert(xhr.responseText);
	});
	
	closeModal();
	usersTable.innerHTML = "";
	loadUsers();
	
}

function showModal(user){
	modal.style.display = "flex";
	id.value = user.id;
	username.placeholder = user.username;
	fname.placeholder = user.firstName;
	lname.placeholder = user.lastName;
	email.placeholder = user.email;
}

function closeModal(){
	modal.style.display = "none";
}

async function fillTable(data){
		const tr = usersTable.insertRow();
		let header = "<th>Id</th>";
		header = header + "<th>Username</th>";
		header = header + "<th>First Name</th>";
		header = header + "<th>Last Name</th>";
		header = header + "<th>Email</th>";
		tr.innerHTML = header;
	
	JSON.parse(data).forEach((user) => {
		
		const refresh = document.createElement("img");
		refresh.src = "<%=request.getContextPath()%>/main/assets/icons/refresh.png";
		refresh.addEventListener("click", function(){
			showModal(user);
		})
		
		const trash = document.createElement("img");
		trash.src = "<%=request.getContextPath()%>/main/assets/icons/trash.png";
		trash.addEventListener("click", function(){
			if(confirm("Delete " + user.username)){					
			const url = "<%=request.getContextPath()%>/main/users/" + user.id;
			$.ajax({url: url, method: "delete"});
			this.parentNode.parentNode.remove();
			}
		})
		
		const tr = usersTable.insertRow();
		tr.insertCell().appendChild(document.createTextNode(user.id));
		tr.insertCell().appendChild(document.createTextNode(user.username));
		tr.insertCell().appendChild(document.createTextNode(user.firstName));
		tr.insertCell().appendChild(document.createTextNode(user.lastName));
		tr.insertCell().appendChild(document.createTextNode(user.email));
		tr.insertCell().appendChild(refresh);
		tr.insertCell().appendChild(trash);
		});
}

async function loadUsersFromSearch(){
	const search = document.getElementById("input-search-firstName");
	const url = "<%=request.getContextPath()%>/main/users?firstName=" + search.value;
	$.get(url, (data) => {
		usersTable.innerHTML = "";
		fillTable(data);
		});
}


async function loadAllUsers (){
	const url = "<%=request.getContextPath()%>/main/users";
	$.get(url, (data) => fillTable(data));
}

loadAllUsers();


</script>
</body>
</html>