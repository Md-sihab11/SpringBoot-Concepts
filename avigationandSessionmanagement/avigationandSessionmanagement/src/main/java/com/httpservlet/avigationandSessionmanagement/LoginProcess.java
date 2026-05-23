package com.httpservlet.avigationandSessionmanagement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.webmvc.autoconfigure.DispatcherServletPath;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

@WebServlet("/Loginproc")
public class LoginProcess extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if("admin123".equals(password))
        {
            request.setAttribute("user","Premium Admin");
           //request.setAttribute("welcomems" +username, "Good to see you in Backend role");
            request.setAttribute("welcomems", "Good to see you in Backend role " + username); // aibhave likh te hobe

            //forwarding the request to the another servlet
            RequestDispatcher  requestDispatcher = request.getRequestDispatcher("/dashboard");
            requestDispatcher.forward(request,response);
        }
        else{

            //if user tap wrong pass then they will be stayed on the same page
                response.sendRedirect("index.html");
        }
    }
}
