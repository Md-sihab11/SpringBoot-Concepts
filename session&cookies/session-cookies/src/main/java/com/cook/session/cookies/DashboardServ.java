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

@WebServlet("/dashboard")
public class DashboardServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_name") == null) {
            response.sendRedirect("login");
            return;
        }

        String username = (String) session.getAttribute("user_name");
        String theme = "light";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("user_theme".equals(c.getName())) {
                    theme = c.getValue();
                    break;
                }
            }
        }

        String bgColor = "dark".equals(theme) ? "#222" : "#FFF";
        String textColor = "dark".equals(theme) ? "#FFF" : "#000";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='background-color:" + bgColor + "; color:" + textColor + "; font-family:Arial; padding:40px;'>");
        out.println("<h1>Welcome, " + username.toUpperCase() + "</h1>");
        out.println("<p>Session ID: <code>" + session.getId() + "</code></p>");
        out.println("<p>Theme preference from Cookie: <b>" + theme.toUpperCase() + "</b></p>");
        out.println("<br><a href='logout' style='color:red; font-weight:bold;'>Logout</a>");
        out.println("</body></html>");
    }
}