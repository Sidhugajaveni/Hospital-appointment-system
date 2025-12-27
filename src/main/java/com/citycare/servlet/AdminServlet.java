package com.citycare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Security check
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("admin-login.html");
            return;
        }

        // Forward to admin dashboard (inside WEB-INF)
        request.getRequestDispatcher("/WEB-INF/admin.html")
               .forward(request, response);
    }
}
