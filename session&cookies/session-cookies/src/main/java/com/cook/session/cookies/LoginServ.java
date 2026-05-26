package com.cook.session.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Login</title></head>");
        out.println("<body style='text-align:center; padding-top:50px; font-family:Arial;'>");
        out.println("<h2>Login to System</h2>");
        out.println("<form action='login' method='POST' style='display:inline-block; border:1px solid #ccc; padding:20px; border-radius:8px;'>");
        out.println("Username: <input type='text' name='username' required><br><br>");
        out.println("Password: <input type='password' name='password' required><br><br>");
        out.println("Theme: <select name='theme'><option value='light'>Light</option><option value='dark'>Dark</option></select><br><br>");
        out.println("<input type='submit' value='Login'>");
        out.println("<br><br><span style='font-size:12px; color:gray;'>Hint: shihab / 1234</span>");
        out.println("</form></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String theme = request.getParameter("theme");

        if ("shihab".equals(user) && "1234".equals(pass)) {
            // সেশন তৈরি করা
            HttpSession session = request.getSession(true);
            session.setAttribute("user_name", user);

            // সেশন টাইমআউট কোড দিয়েই সেট করে দিলাম (৩০ মিনিট = ৩০ * ৬০ সেকেন্ড)
            session.setMaxInactiveInterval(30 * 60);

            // কুকি তৈরি করা
            Cookie themeCookie = new Cookie("user_theme", theme);
            themeCookie.setMaxAge(60 * 60 * 24); // ১ দিন মেয়াদ
            themeCookie.setPath(request.getContextPath());
            response.addCookie(themeCookie);

            response.sendRedirect("dashboard");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h4 style='color:red; text-align:center;'>Wrong Credentials!</h4>");
            doGet(request, response);
        }
    }
}