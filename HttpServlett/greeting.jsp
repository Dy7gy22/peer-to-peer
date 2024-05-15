<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Salutations</title>
</head>
<body>
    <h1>Greetings <%= request.getParameter("nom").toUpperCase() %>!</h1>
    <% Double gagne = (Double) request.getAttribute("gagne"); %>
    Vous avez gagné : <%= gagne %> millions de dollars!
</body>
</html>
