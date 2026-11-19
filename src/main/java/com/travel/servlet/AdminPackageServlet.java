package com.travel.servlet;

import com.travel.dao.PackageDAO;
import com.travel.model.TravelPackage;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/admin/packages")
public class AdminPackageServlet extends HttpServlet {
    private PackageDAO packageDAO = new PackageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            packageDAO.deletePackage(id);
            resp.sendRedirect(ctx + "/admin/packages");
            return;
        }

        TravelPackage editPkg = null;
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            editPkg = packageDAO.getPackageById(id);
        }

        List<TravelPackage> packages = packageDAO.getAllPackages();

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("Manage Packages", ctx));
        out.println(HtmlUtil.adminNavbar(ctx));

        out.println("<div class=\"container\">");
        out.println("<h2 style=\"color:#1e3a5f;margin-bottom:20px;\">" + (editPkg != null ? "Edit Package" : "Add New Package") + "</h2>");

        // Add/Edit form
        out.println("<div class=\"form-box\" style=\"max-width:600px;\">");
        out.println("<form method=\"post\" action=\"" + ctx + "/admin/packages\">");
        if (editPkg != null) {
            out.println("<input type=\"hidden\" name=\"id\" value=\"" + editPkg.getId() + "\">");
        }
        out.println("<div class=\"form-group\"><label>Package Name</label>");
        out.println("<input type=\"text\" name=\"name\" value=\"" + (editPkg != null ? HtmlUtil.escHtml(editPkg.getName()) : "") + "\" required></div>");
        out.println("<div class=\"form-group\"><label>Destination</label>");
        out.println("<input type=\"text\" name=\"destination\" value=\"" + (editPkg != null ? HtmlUtil.escHtml(editPkg.getDestination()) : "") + "\" required></div>");
        out.println("<div class=\"form-group\"><label>Description</label>");
        out.println("<textarea name=\"description\">" + (editPkg != null ? HtmlUtil.escHtml(editPkg.getDescription()) : "") + "</textarea></div>");
        out.println("<div class=\"form-group\"><label>Duration (Days)</label>");
        out.println("<input type=\"number\" name=\"durationDays\" value=\"" + (editPkg != null ? editPkg.getDurationDays() : "") + "\" min=\"1\" required></div>");
        out.println("<div class=\"form-group\"><label>Price per Person (&#8377;)</label>");
        out.println("<input type=\"number\" name=\"price\" value=\"" + (editPkg != null ? editPkg.getPrice() : "") + "\" step=\"0.01\" min=\"0\" required></div>");
        out.println("<div class=\"form-group\"><label>Available Slots</label>");
        out.println("<input type=\"number\" name=\"availableSlots\" value=\"" + (editPkg != null ? editPkg.getAvailableSlots() : "") + "\" min=\"0\" required></div>");
        out.println("<div class=\"form-group\"><label>Image URL (optional)</label>");
        out.println("<input type=\"text\" name=\"imageUrl\" value=\"" + (editPkg != null && editPkg.getImageUrl() != null ? HtmlUtil.escHtml(editPkg.getImageUrl()) : "") + "\"></div>");
        out.println("<button type=\"submit\" class=\"btn btn-primary\" style=\"width:100%;\">" + (editPkg != null ? "Update Package" : "Add Package") + "</button>");
        if (editPkg != null) {
            out.println("<a href=\"" + ctx + "/admin/packages\" class=\"btn btn-danger\" style=\"width:100%;text-align:center;margin-top:8px;display:block;\">Cancel</a>");
        }
        out.println("</form>");
        out.println("</div>");

        // Packages table
        out.println("<h3 style=\"color:#1e3a5f;margin:30px 0 12px;\">All Packages</h3>");
        out.println("<table>");
        out.println("<thead><tr><th>ID</th><th>Name</th><th>Destination</th><th>Days</th><th>Price</th><th>Slots</th><th>Actions</th></tr></thead>");
        out.println("<tbody>");
        for (TravelPackage p : packages) {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + HtmlUtil.escHtml(p.getName()) + "</td>");
            out.println("<td>" + HtmlUtil.escHtml(p.getDestination()) + "</td>");
            out.println("<td>" + p.getDurationDays() + "</td>");
            out.println("<td>&#8377;" + HtmlUtil.formatPrice(p.getPrice()) + "</td>");
            out.println("<td>" + p.getAvailableSlots() + "</td>");
            out.println("<td>");
            out.println("<a href=\"" + ctx + "/admin/packages?action=edit&id=" + p.getId() + "\" class=\"btn btn-primary btn-sm\">Edit</a> ");
            out.println("<a href=\"" + ctx + "/admin/packages?action=delete&id=" + p.getId() + "\" class=\"btn btn-danger btn-sm\" onclick=\"return confirm('Delete this package?');\">Delete</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</tbody></table>");

        out.println("</div>");
        out.println(HtmlUtil.pageEnd());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");

        TravelPackage pkg = new TravelPackage();
        pkg.setName(req.getParameter("name"));
        pkg.setDestination(req.getParameter("destination"));
        pkg.setDescription(req.getParameter("description"));
        pkg.setDurationDays(Integer.parseInt(req.getParameter("durationDays")));
        pkg.setPrice(new BigDecimal(req.getParameter("price")));
        pkg.setAvailableSlots(Integer.parseInt(req.getParameter("availableSlots")));
        pkg.setImageUrl(req.getParameter("imageUrl"));

        if (idStr != null && !idStr.isEmpty()) {
            pkg.setId(Integer.parseInt(idStr));
            packageDAO.updatePackage(pkg);
        } else {
            packageDAO.addPackage(pkg);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/packages");
    }
}
