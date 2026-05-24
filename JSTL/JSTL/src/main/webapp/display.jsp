<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product Catalog</title>
</head>
<body>
    <h2>Welcome, our ${userStatus} Customer!</h2>

    <h3>Available Products:</h3>
    <ul>
        <c:forEach var="product" items="${items}">
            <li>Product Name: <strong>${product}</strong></li>
        </c:forEach>
    </ul>

    <c:if test="${userStatus == 'VIP'}">
        <p style="color: gold; font-weight: bold;">🎉 You get a 10% special discount on your purchase!</p>
    </c:if>
</body>
</html>