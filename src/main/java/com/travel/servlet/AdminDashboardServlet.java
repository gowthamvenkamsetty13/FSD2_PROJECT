package com.travel.servlet;

import com.travel.dao.BookingDAO;
import com.travel.dao.PackageDAO;
import com.travel.model.Booking;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private BookingDAO bookingDAO = new BookingDAO();
    private PackageDAO packageDAO = new PackageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();

        int totalBookings = bookingDAO.countBookings();
        int totalUsers = bookingDAO.countUsers();
        int totalPackages = packageDAO.getAllPackages().size();
        List<Booking> allBookings = bookingDAO.getAllBookings();

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("Admin Dashboard", ctx));
        out.println(HtmlUtil.adminNavbar(ctx));

        out.println("<div class=\"container\">");
        out.println("<h2 style=\"color:#1e3a5f;margin-bottom:20px;\">Admin Dashboard</h2>");

        // Stats cards
        out.println("<div class=\"stats\">");
        out.println("<div class=\"stat-card\"><div class=\"num\">" + totalPackages + "</div><div class=\"label\">Travel Packages</div></div>");
        out.println("<div class=\"stat-card\"><div class=\"num\">" + totalBookings + "</div><div class=\"label\">Active Bookings</div></div>");
        out.println("<div class=\"stat-card\"><div class=\"num\">" + totalUsers + "</div><div class=\"label\">Registered Users</div></div>");
        out.println("</div>");

        // Bookings table
        out.println("<h3 style=\"color:#1e3a5f;margin-bottom:12px;\">Recent Bookings</h3>");
        if (allBookings.isEmpty()) {
            out.println("<div class=\"card\" style=\"text-align:center;\">No bookings yet.</div>");
        } else {
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>Customer</th><th>Package</th><th>Destination</th><th>Travelers</th><th>Total</th><th>Travel Date</th><th>Status</th></tr></thead>");
            out.println("<tbody>");
            for (Booking b : allBookings) {
                out.println("<tr>");
                out.println("<td>" + b.getId() + "</td>");
                out.println("<td>" + HtmlUtil.escHtml(b.getUserName()) + "</td>");
                out.println("<td>" + HtmlUtil.escHtml(b.getPackageName()) + "</td>");
                out.println("<td>" + HtmlUtil.escHtml(b.getDestination()) + "</td>");
                out.println("<td>" + b.getNumTravelers() + "</td>");
                out.println("<td>&#8377;" + HtmlUtil.formatPrice(b.getTotalPrice()) + "</td>");
                out.println("<td>" + b.getTravelDate() + "</td>");
                String badgeClass = "CONFIRMED".equals(b.getStatus()) ? "badge-green" : "badge-red";
                out.println("<td><span class=\"badge " + badgeClass + "\">" + b.getStatus() + "</span></td>");
                out.println("</tr>");
            }
            out.println("</tbody></table>");
        }

        out.println("</div>");
        out.println(HtmlUtil.pageEnd());
    }
}
