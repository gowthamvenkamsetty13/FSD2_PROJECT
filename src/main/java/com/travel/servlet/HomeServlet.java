package com.travel.servlet;

import com.travel.model.User;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("Home", ctx));
        out.println(HtmlUtil.navbar(ctx, user));

        out.println("<div class=\"hero\">");
        out.println("<h1>Explore the World with TravelGo</h1>");
        out.println("<p>Discover amazing travel packages at unbeatable prices. Book your dream vacation today!</p>");
        out.println("<a href=\"" + ctx + "/packages\" class=\"btn btn-primary\" style=\"padding:12px 30px;font-size:16px;\">Browse Packages</a>");
        out.println("</div>");

        out.println("<div class=\"container\">");
        out.println("<h2 style=\"text-align:center;margin-bottom:20px;color:#1e3a5f;\">Why Choose TravelGo?</h2>");
        out.println("<div class=\"grid\">");

        out.println("<div class=\"card\" style=\"text-align:center;\">");
        out.println("<h3>&#127758; Curated Destinations</h3>");
        out.println("<p>Hand-picked destinations across India and beyond, covering beaches, mountains, heritage sites and more.</p>");
        out.println("</div>");

        out.println("<div class=\"card\" style=\"text-align:center;\">");
        out.println("<h3>&#128176; Best Prices</h3>");
        out.println("<p>Competitive pricing with no hidden charges. Get the best deals on every trip.</p>");
        out.println("</div>");

        out.println("<div class=\"card\" style=\"text-align:center;\">");
        out.println("<h3>&#128222; 24/7 Support</h3>");
        out.println("<p>Our support team is available round the clock to help with any queries.</p>");
        out.println("</div>");

        out.println("</div>");
        out.println("</div>");

        out.println(HtmlUtil.pageEnd());
    }
}
