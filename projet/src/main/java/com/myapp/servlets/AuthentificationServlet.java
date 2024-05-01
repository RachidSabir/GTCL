package com.myapp.servlets;

import java.io.IOException;

import com.myapp.models.UtilisateurService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("NewFile.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String pwd = request.getParameter("pwd");
	    UtilisateurService utilservice = new UtilisateurService();

	    if(utilservice.ValideUtilisateur(email, pwd)) {
	        Cookie loginCookie = new Cookie("email", email);
	        loginCookie.setMaxAge(30*60);
	        response.addCookie(loginCookie);
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    } else {
	        // Set an error attribute in request scope
	        request.setAttribute("error", "Invalid email or password!");

	        // Forward the request to NewFile.jsp to display the error message
	        request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
	    }
	}


}
