package com.httpservlet.getandpost;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class Servelett extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2>Sorry, here is something wrong. Resister first then submit</h2>");

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name=req.getParameter("username");
        String email=req.getParameter("email");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("String name is: "+name);
        out.println("\n");
        out.println("String email is: "+email);
    }
}
