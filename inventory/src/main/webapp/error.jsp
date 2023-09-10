<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>Error</title>

<style>

	.container {
		background-image: linear-gradient(180deg, blue 0%, black 100%);
		color: white;
		display: flex;
		flex-direction: column;
		align-items: center;
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		padding-top: 100px;
		
	}

</style>

</head>
<body>
	<div class="container">
		<h1>Please, call to support!</h1>
		<div>
			<%= 
				request.getAttribute("msg")
			%>
		</div>
	</div>
</body>
</html>