package com.cook.session.cookies;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // সেশন ধ্বংস করা
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // কুকি ধ্বংস করা (MaxAge = 0)
        Cookie killCookie = new Cookie("user_theme", "");
        killCookie.setMaxAge(0);
        killCookie.setPath(request.getContextPath());
        response.addCookie(killCookie);

        response.sendRedirect("login");
    }
}