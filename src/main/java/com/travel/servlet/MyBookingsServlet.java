package com.travel.servlet;

import com.travel.dao.BookingDAO;
import com.travel.model.Booking;
import com.travel.model.User;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/my-bookings")
public class MyBookingsServlet extends HttpServlet {
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        User user = (User) req.getSession().getAttribute("user");
        List<Booking> bookings = bookingDAO.getBookingsByUser(user.getId());
        String success = req.getParameter("success");

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("My Bookings", ctx));
        out.println(HtmlUtil.navbar(ctx, user));

        out.println("<div class=\"container\">");
        out.println("<h2 style=\"color:#1e3a5f;margin-bottom:20px;\">My Bookings</h2>");

        if ("true".equals(success)) {
            out.println(HtmlUtil.alertSuccess("Booking confirmed successfully!"));
        }

        if (bookings.isEmpty()) {
            out.println("<div class=\"card\" style=\"text-align:center;\"><p>No bookings yet. <a href=\"" + ctx + "/packages\">Browse packages</a> to get started!</p></div>");
        } else {
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>Package</th><th>Destination</th><th>Travelers</th><th>Total Price</th><th>Travel Date</th><th>Status</th><th>Action</th></tr></thead>");
            out.println("<tbody>");
            for (Booking b : bookings) {
                out.println("<tr>");
                out.println("<td>" + b.getId() + "</td>");
                out.println("<td>" + HtmlUtil.escHtml(b.getPackageName()) + "</td>");
                out.println("<td>" + HtmlUtil.escHtml(b.getDestination()) + "</td>");
                out.println("<td>" + b.getNumTravelers() + "</td>");
                out.println("<td>&#8377;" + HtmlUtil.formatPrice(b.getTotalPrice()) + "</td>");
                out.println("<td>" + b.getTravelDate() + "</td>");
                String badgeClass = "CONFIRMED".equals(b.getStatus()) ? "badge-green" : "badge-red";
                out.println("<td><span class=\"badge " + badgeClass + "\">" + b.getStatus() + "</span></td>");
                out.println("<td>");
                if ("CONFIRMED".equals(b.getStatus())) {
                    out.println("<a href=\"" + ctx + "/cancel-booking?id=" + b.getId() + "\" class=\"btn btn-danger btn-sm\" onclick=\"return confirm('Cancel this booking?');\">Cancel</a>");
                }
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody></table>");
        }

        out.println("</div>");
        out.println(HtmlUtil.pageEnd());
    }
}
