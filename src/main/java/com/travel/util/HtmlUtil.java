package com.travel.util;

import com.travel.model.User;

public class HtmlUtil {

    public static String pageStart(String title, String contextPath) {
        return "<!DOCTYPE html>\n<html>\n<head>\n" +
               "<meta charset=\"UTF-8\">\n" +
               "<title>" + title + " - TravelGo</title>\n" +
               "<link rel=\"stylesheet\" href=\"" + contextPath + "/css/style.css\">\n" +
               "</head>\n<body>\n";
    }

    public static String navbar(String ctx, User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"navbar\">\n");
        sb.append("<a href=\"" + ctx + "/\" class=\"brand\">&#9992; TravelGo</a>\n");
        sb.append("<div class=\"nav-links\">\n");
        sb.append("<a href=\"" + ctx + "/packages\">Packages</a>\n");
        if (user != null) {
            sb.append("<a href=\"" + ctx + "/my-bookings\">My Bookings</a>\n");
            if (user.isAdmin()) {
                sb.append("<a href=\"" + ctx + "/admin/dashboard\">Admin</a>\n");
            }
            sb.append("<a href=\"" + ctx + "/logout\">Logout (" + escHtml(user.getUsername()) + ")</a>\n");
        } else {
            sb.append("<a href=\"" + ctx + "/login\">Login</a>\n");
            sb.append("<a href=\"" + ctx + "/register\">Register</a>\n");
        }
        sb.append("</div>\n</div>\n");
        return sb.toString();
    }

    public static String adminNavbar(String ctx) {
        return "<div class=\"navbar\">\n" +
               "<a href=\"" + ctx + "/\" class=\"brand\">&#9992; TravelGo Admin</a>\n" +
               "<div class=\"nav-links\">\n" +
               "<a href=\"" + ctx + "/admin/dashboard\">Dashboard</a>\n" +
               "<a href=\"" + ctx + "/admin/packages\">Manage Packages</a>\n" +
               "<a href=\"" + ctx + "/packages\">View Site</a>\n" +
               "<a href=\"" + ctx + "/logout\">Logout</a>\n" +
               "</div>\n</div>\n";
    }

    public static String pageEnd() {
        return "<div class=\"footer\">&copy; 2026 TravelGo. All rights reserved.</div>\n" +
               "</body>\n</html>";
    }

    public static String alertError(String msg) {
        if (msg == null) return "";
        return "<div class=\"alert alert-error\">" + escHtml(msg) + "</div>\n";
    }

    public static String alertSuccess(String msg) {
        if (msg == null) return "";
        return "<div class=\"alert alert-success\">" + escHtml(msg) + "</div>\n";
    }

    public static String escHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    public static String formatPrice(java.math.BigDecimal price) {
        if (price == null) return "0";
        return String.format("%,.0f", price);
    }
}
