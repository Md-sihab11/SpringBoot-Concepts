package com.servlet.contexConfig;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Servlett extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext ct = getServletContext();
        String contextPath = ct.getInitParameter("db_url");

        ServletConfig con =  getServletConfig();
        String config = con.getInitParameter("merchant_id");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Configuration Parameters Demo</h2>");
        out.println("<p><strong>ServletConfig (Local MID):</strong> " + config + "</p>");
        out.println("<p><strong>ServletContext (Global DB URL):</strong> " + contextPath + "</p>");
        out.println("</body></html>");


    }
}
