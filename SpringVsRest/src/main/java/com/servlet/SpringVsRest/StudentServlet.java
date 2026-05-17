package com.servlet.SpringVsRest;
import com.servlet.SpringVsRest.POJOModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student/raw")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        POJOModel s= new POJOModel("Shakib", 20);

            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");

        String jsonResponse = "{\n" +
                "  \"name\": \"" + s.getName() + "\",\n" +
                "  \"age\": " + s.getAge() + "\n" +
                "}";
        //sending response
        res.getWriter().write(jsonResponse);
    }
}
