package com.el.JSTL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ১. একটা ডামি প্রোডাক্ট লিস্ট তৈরি করলাম
        List<String> productList = new ArrayList<>();
        productList.add("Laptop");
        productList.add("iPhone 17 Pro"); // তোমার পছন্দের সেই গোল্ডেন আইফোন!
        productList.add("Smart Watch");

        // ২. রিকোয়েস্টে ডেটা সেট করলাম
        request.setAttribute("items", productList);
        request.setAttribute("userStatus", "VIP");

        // ৩. forward করে দিলাম জেএসপি পেজে
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }
}