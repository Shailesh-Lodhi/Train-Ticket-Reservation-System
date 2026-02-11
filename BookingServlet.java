
package com.train.booking;
import com.train.db.DBConnection;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String trainId = request.getParameter("train_id");
        PrintWriter out = response.getWriter();

        out.println("<form method='post'>");
        out.println("Seats to Book: <input type='number' name='seats'><br>");
        out.println("<input type='hidden' name='train_id' value='" + trainId + "'>");
        out.println("<button type='submit'>Confirm</button>");
        out.println("</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        int trainId = Integer.parseInt(request.getParameter("train_id"));
        int seats = Integer.parseInt(request.getParameter("seats"));

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement(
                "UPDATE trains SET seats = seats - ? WHERE train_id = ?"
            );
            ps1.setInt(1, seats);
            ps1.setInt(2, trainId);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO bookings (username, train_id, seats_booked) VALUES (?, ?, ?)"
            );
            ps2.setString(1, username);
            ps2.setInt(2, trainId);
            ps2.setInt(3, seats);
            ps2.executeUpdate();

            response.getWriter().print("Booking Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
