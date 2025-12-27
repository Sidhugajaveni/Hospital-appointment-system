package com.citycare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hardcoded admin credentials
        if ("admin".equals(username) && "admin123".equals(password)) {

            HttpSession session = request.getSession();
            session.setAttribute("admin", "true");

            // Redirect to AdminServlet
            response.sendRedirect("admin");

        } else {
            response.setContentType("text/html");
            response.getWriter().println(
                "<h3 style='color:red;text-align:center;'>Invalid Username or Password</h3>" +
                "<p style='text-align:center;'><a href='admin-login.html'>Try Again</a></p>"
            );
        }
    }
}
