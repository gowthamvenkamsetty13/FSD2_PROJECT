package com.travel.servlet;

import com.travel.dao.UserDAO;
import com.travel.model.User;
import com.travel.util.HtmlUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        String error = (String) req.getAttribute("error");

        PrintWriter out = resp.getWriter();
        out.println(HtmlUtil.pageStart("Register", ctx));
        out.println(HtmlUtil.navbar(ctx, null));

        out.println("<div class=\"form-box\">");
        out.println("<h2>Create Account</h2>");

        if (error != null) {
            out.println(HtmlUtil.alertError(error));
        }

        out.println("<form method=\"post\" action=\"" + ctx + "/register\">");
        out.println("<div class=\"form-group\"><label>Full Name</label>");
        out.println("<input type=\"text\" name=\"fullName\" required></div>");
        out.println("<div class=\"form-group\"><label>Username</label>");
        out.println("<input type=\"text\" name=\"username\" required></div>");
        out.println("<div class=\"form-group\"><label>Email</label>");
        out.println("<input type=\"email\" name=\"email\" required></div>");
        out.println("<div class=\"form-group\"><label>Phone</label>");
        out.println("<input type=\"text\" name=\"phone\"></div>");
        out.println("<div class=\"form-group\"><label>Password</label>");
        out.println("<input type=\"password\" name=\"password\" required></div>");
        out.println("<button type=\"submit\" class=\"btn btn-primary\" style=\"width:100%;\">Register</button>");
        out.println("</form>");
        out.println("<p style=\"text-align:center;margin-top:14px;font-size:14px;\">Already have an account? <a href=\"" + ctx + "/login\">Login here</a></p>");
        out.println("</div>");

        out.println(HtmlUtil.pageEnd());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");

        if (userDAO.usernameExists(username)) {
            req.setAttribute("error", "Username already taken");
            doGet(req, resp);
            return;
        }
        if (userDAO.emailExists(email)) {
            req.setAttribute("error", "Email already registered");
            doGet(req, resp);
            return;
        }

        User user = new User(username, email, password, fullName, phone);
        if (userDAO.register(user)) {
            resp.sendRedirect(req.getContextPath() + "/login?registered=true");
        } else {
            req.setAttribute("error", "Registration failed. Please try again.");
            doGet(req, resp);
        }
    }
}
