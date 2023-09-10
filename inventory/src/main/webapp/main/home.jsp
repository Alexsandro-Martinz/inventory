<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First | Home</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/general.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/header.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/assets/css/home.css">
</head>
<body>

	<jsp:include page="assets/jsp/header.jsp"></jsp:include>

	<main class="container">
		<h1 class="title">Home page welcome</h1>
	</main>
	
	<jsp:include page="/main/assets/jsp/scriptsJquery.jsp"></jsp:include>
</body>
</html>