package com.travel.servlet;

import com.travel.dao.PackageDAO;
import com.travel.model.TravelPackage;
import com.travel.model.User;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/packages")
public class PackageListServlet extends HttpServlet {
    private PackageDAO packageDAO = new PackageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        List<TravelPackage> packages = packageDAO.getAllPackages();

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("Travel Packages", ctx));
        out.println(HtmlUtil.navbar(ctx, user));

        out.println("<div class=\"container\">");
        out.println("<h2 style=\"color:#1e3a5f;margin-bottom:20px;\">Available Travel Packages</h2>");

        if (packages.isEmpty()) {
            out.println("<div class=\"card\" style=\"text-align:center;\">No travel packages available at the moment.</div>");
        } else {
            out.println("<div class=\"grid\">");
            for (TravelPackage pkg : packages) {
                out.println("<div class=\"card\">");
                out.println("<h3>" + HtmlUtil.escHtml(pkg.getName()) + "</h3>");
                out.println("<p><strong>Destination:</strong> " + HtmlUtil.escHtml(pkg.getDestination()) + "</p>");
                out.println("<p>" + HtmlUtil.escHtml(pkg.getDescription()) + "</p>");
                out.println("<p><strong>Duration:</strong> " + pkg.getDurationDays() + " Days</p>");
                out.println("<p><strong>Available Slots:</strong> " + pkg.getAvailableSlots() + "</p>");
                out.println("<div style=\"margin-top:12px;display:flex;justify-content:space-between;align-items:center;\">");
                out.println("<span class=\"price\">&#8377;" + HtmlUtil.formatPrice(pkg.getPrice()) + " <small>/person</small></span>");
                if (pkg.getAvailableSlots() > 0) {
                    out.println("<a href=\"" + ctx + "/book?id=" + pkg.getId() + "\" class=\"btn btn-primary\">Book Now</a>");
                } else {
                    out.println("<span class=\"badge badge-red\">Sold Out</span>");
                }
                out.println("</div>");
                out.println("</div>");
            }
            out.println("</div>");
        }

        out.println("</div>");
        out.println(HtmlUtil.pageEnd());
    }
}
