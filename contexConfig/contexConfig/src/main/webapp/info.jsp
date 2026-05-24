<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP Practice</title>
</head>
<body>
    <h2>Welcome to JSP Learning, Sharar!</h2>

    <%!
        int accessCount = 0;
        public String getGreeting() {
            return "হ্যালো ব্যাকএন্ড ইঞ্জিনিয়ার!";
        }
    %>

    <%
        accessCount++;
        Date now = new Date();
    %>

    <p><strong>গ্রিটিংস:</strong> <%= getGreeting() %></p>
    <p><strong>সার্ভার কারেন্ট টাইম:</strong> <%= now %></p>
    <p><strong>এই পেজটি মোট ভিজিট হয়েছে:</strong> <%= accessCount %> বার।</p>

</body>
</html>