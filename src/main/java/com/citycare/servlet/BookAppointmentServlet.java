package com.citycare.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookAppointment")
public class BookAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        String type = request.getParameter("type");

        int token;
        if ("Emergency".equals(type)) {
            token = new Random().nextInt(5) + 1;
        } else {
            token = new Random().nextInt(20) + 10;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_db",
                    "root",
                    "1234"
            );

            String sql = "INSERT INTO appointments (name, gender, age, phone, department, appointment_type, token) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setInt(3, age);
            ps.setString(4, phone);
            ps.setString(5, department);
            ps.setString(6, type);
            ps.setInt(7, token);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("confirmation.html?token=" + token);
    }
}
