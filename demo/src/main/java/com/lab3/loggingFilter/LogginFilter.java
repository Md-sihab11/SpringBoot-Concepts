package com.lab3.loggingFilter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/*")
public class LogginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("Login filter has been initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //Directly http method pawa jay na tai downcast kore nite hoy
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        //before request ( pre-processing)
        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();

        System.out.println("\n[FILTER - INCOMING] ---> Request Received!");
        System.out.println("[FILTER] Method: " + method + " | URL: " + uri);

         // ⚙️ ২. STEP 2: রিকোয়েস্টটিকে পরবর্তী ধাপ বা কন্ট্রোলারের দিকে পাঠিয়ে দেওয়া
        // এই লাইনটি না লিখলে রিকোয়েস্ট এখানেই আটকে যাবে, ব্রাউজারে কিছুই লোড হবে না!
        filterChain.doFilter(servletRequest, servletResponse);

        // 🟢 ৩. STEP 3: কন্ট্রোলার বা সার্ভলেটের কাজ শেষ হওয়ার পরের লগ (Post-processing)
        System.out.println("[FILTER - OUTGOING] <--- Response complete for: " + uri);
    }

    @Override
    public void destroy() {

        //when filter remove last time
        System.out.println("Login Filter destroyed");
    }
}
