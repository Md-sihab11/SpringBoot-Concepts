package com.servlet.threadsafe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/thread")
public class threadtracker extends HttpServlet {

    private final AtomicInteger count = new  AtomicInteger(0);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       int currentcount = count.incrementAndGet();

        String threadname= Thread.currentThread().getName();
        System.out.println("ThreadName: "+threadname+"Thread count: " +currentcount);

        response.setContentType("text/html");
        response.getWriter().write("<h1>"+threadname+"</h1>");
    }
}
