
package com.train.train;
import com.train.db.DBConnection;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TrainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Available Trains</h2>");

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM trains");

            while (rs.next()) {
                out.println("<div>");
                out.println("<b>" + rs.getString("train_name") + "</b><br>");
                out.println(rs.getString("source") + " → " + rs.getString("destination") + "<br>");
                out.println("Seats Available: " + rs.getInt("seats") + "<br>");
                out.println("<a href='book?train_id=" + rs.getInt("train_id") + "'>Book</a>");
                out.println("<hr></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
