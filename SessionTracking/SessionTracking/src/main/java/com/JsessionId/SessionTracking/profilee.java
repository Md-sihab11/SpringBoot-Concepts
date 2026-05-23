package com.JsessionId.SessionTracking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class profilee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(session != null && session.getAttribute("currUser")!=null){
            String name = (String)session.getAttribute("currUser"); //small mistake i did req.getattribute dicilam


            out.println("<html><body>");
            out.println("<h1>স্বাগতম, " + name + "!</h1>");
            out.println("<p>তুমি সাকসেসফুলি লগইন অবস্থায় আছো।</p>");
            out.println("<a href='logout'>Log Out</a>");
            out.println("</body></html>");
           // out.print("<h3>"+session.getAttribute("currUser")+"</h3>");
        }
        else{
           out.println("Kire shihab!! Please login first and then enter into it...");
        }
    }
}
