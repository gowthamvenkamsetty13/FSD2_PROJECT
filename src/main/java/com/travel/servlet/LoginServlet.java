package com.travel.servlet;

import com.travel.dao.UserDAO;
import com.travel.model.User;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        String error = (String) req.getAttribute("error");
        String registered = req.getParameter("registered");

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("Login", ctx));
        out.println(HtmlUtil.navbar(ctx, null));

        out.println("<div class=\"form-box\">");
        out.println("<h2>Login</h2>");

        if ("true".equals(registered)) {
            out.println(HtmlUtil.alertSuccess("Registration successful! Please login."));
        }
        if (error != null) {
            out.println(HtmlUtil.alertError(error));
        }

        out.println("<form method=\"post\" action=\"" + ctx + "/login\">");
        out.println("<div class=\"form-group\"><label>Username</label>");
        out.println("<input type=\"text\" name=\"username\" required></div>");
        out.println("<div class=\"form-group\"><label>Password</label>");
        out.println("<input type=\"password\" name=\"password\" required></div>");
        out.println("<button type=\"submit\" class=\"btn btn-primary\" style=\"width:100%;\">Login</button>");
        out.println("</form>");
        out.println("<p style=\"text-align:center;margin-top:14px;font-size:14px;\">Don't have an account? <a href=\"" + ctx + "/register\">Register here</a></p>");
        out.println("</div>");

        out.println(HtmlUtil.pageEnd());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            if (user.isAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            } else {
                resp.sendRedirect(req.getContextPath() + "/packages");
            }
        } else {
            req.setAttribute("error", "Invalid username or password");
            doGet(req, resp);
        }
    }
}
