package com.citycare.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewAppointments")
public class ViewAppointmentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String[]> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_db",
                "root",
                "1234"
            );

            Statement st = con.createStatement();

            // Emergency first, then token number
            ResultSet rs = st.executeQuery(
                "SELECT name, gender, phone, department, appointment_type, token " +
                "FROM appointments " +
                "ORDER BY appointment_type='Emergency' DESC, token ASC"
            );

            while (rs.next()) {
                String[] row = new String[6];
                row[0] = rs.getString("name");
                row[1] = rs.getString("gender");
                row[2] = rs.getString("phone");
                row[3] = rs.getString("department");
                row[4] = rs.getString("appointment_type");
                row[5] = rs.getString("token");

                list.add(row);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("appointments", list);

        // VERY IMPORTANT LINE
        request.getRequestDispatcher("/WEB-INF/viewAppointments.jsp")
               .forward(request, response);
    }
}
