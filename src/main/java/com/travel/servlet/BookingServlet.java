package com.travel.servlet;

import com.travel.dao.BookingDAO;
import com.travel.dao.PackageDAO;
import com.travel.model.*;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {
    private PackageDAO packageDAO = new PackageDAO();
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        User user = (User) req.getSession().getAttribute("user");

        int pkgId;
        try {
            pkgId = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e) {
            resp.sendRedirect(ctx + "/packages");
            return;
        }

        TravelPackage pkg = packageDAO.getPackageById(pkgId);
        if (pkg == null) {
            resp.sendRedirect(ctx + "/packages");
            return;
        }

        String error = (String) req.getAttribute("error");

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("Book Package", ctx));
        out.println(HtmlUtil.navbar(ctx, user));

        out.println("<div class=\"container\" style=\"max-width:600px;\">");

        // Package info card
        out.println("<div class=\"card\">");
        out.println("<h2 style=\"color:#1e3a5f;margin-bottom:16px;\">Book: " + HtmlUtil.escHtml(pkg.getName()) + "</h2>");
        out.println("<p><strong>Destination:</strong> " + HtmlUtil.escHtml(pkg.getDestination()) + "</p>");
        out.println("<p><strong>Duration:</strong> " + pkg.getDurationDays() + " Days</p>");
        out.println("<p><strong>Price:</strong> <span class=\"price\">&#8377;" + HtmlUtil.formatPrice(pkg.getPrice()) + "</span> <small>/person</small></p>");
        out.println("<p><strong>Available Slots:</strong> " + pkg.getAvailableSlots() + "</p>");
        out.println("</div>");

        if (error != null) {
            out.println(HtmlUtil.alertError(error));
        }

        // Booking form
        out.println("<div class=\"form-box\" style=\"margin-top:0;\">");
        out.println("<h2>Booking Details</h2>");
        out.println("<form method=\"post\" action=\"" + ctx + "/book\">");
        out.println("<input type=\"hidden\" name=\"packageId\" value=\"" + pkg.getId() + "\">");
        out.println("<div class=\"form-group\"><label>Number of Travelers</label>");
        out.println("<input type=\"number\" name=\"numTravelers\" min=\"1\" max=\"" + pkg.getAvailableSlots() + "\" value=\"1\" required></div>");
        out.println("<div class=\"form-group\"><label>Travel Date</label>");
        out.println("<input type=\"date\" name=\"travelDate\" required></div>");
        out.println("<button type=\"submit\" class=\"btn btn-success\" style=\"width:100%;\">Confirm Booking</button>");
        out.println("</form>");
        out.println("</div>");

        out.println("</div>");
        out.println(HtmlUtil.pageEnd());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ctx = req.getContextPath();
        User user = (User) req.getSession().getAttribute("user");
        int pkgId = Integer.parseInt(req.getParameter("packageId"));
        int numTravelers = Integer.parseInt(req.getParameter("numTravelers"));
        String travelDateStr = req.getParameter("travelDate");

        TravelPackage pkg = packageDAO.getPackageById(pkgId);
        if (pkg == null) {
            resp.sendRedirect(ctx + "/packages");
            return;
        }

        if (numTravelers > pkg.getAvailableSlots()) {
            req.setAttribute("error", "Not enough slots available. Only " + pkg.getAvailableSlots() + " left.");
            req.setAttribute("id", String.valueOf(pkgId));
            doGet(req, resp);
            return;
        }

        BigDecimal totalPrice = pkg.getPrice().multiply(BigDecimal.valueOf(numTravelers));

        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setPackageId(pkgId);
        booking.setNumTravelers(numTravelers);
        booking.setTotalPrice(totalPrice);
        booking.setTravelDate(Date.valueOf(travelDateStr));

        if (packageDAO.reduceSlots(pkgId, numTravelers) && bookingDAO.createBooking(booking)) {
            resp.sendRedirect(ctx + "/my-bookings?success=true");
        } else {
            req.setAttribute("error", "Booking failed. Please try again.");
            doGet(req, resp);
        }
    }
}
