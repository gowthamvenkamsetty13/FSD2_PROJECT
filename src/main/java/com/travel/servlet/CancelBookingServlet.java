package com.travel.servlet;

import com.travel.dao.BookingDAO;
import com.travel.dao.PackageDAO;
import com.travel.model.Booking;
import com.travel.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/cancel-booking")
public class CancelBookingServlet extends HttpServlet {
    private BookingDAO bookingDAO = new BookingDAO();
    private PackageDAO packageDAO = new PackageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        int bookingId = Integer.parseInt(req.getParameter("id"));

        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking != null && booking.getUserId() == user.getId()) {
            if (bookingDAO.cancelBooking(bookingId)) {
                packageDAO.restoreSlots(booking.getPackageId(), booking.getNumTravelers());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/my-bookings");
    }
}
