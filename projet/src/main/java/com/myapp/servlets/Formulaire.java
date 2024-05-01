package com.myapp.servlets;


import java.io.IOException;

import com.myapp.models.Utilisateur;
import com.myapp.models.UtilisateurService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Formulaire")
public class Formulaire extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastename = request.getParameter("lastname");
		String adress = request.getParameter("adress");
		String pwd = request.getParameter("pwd");
		Cookie userCookie = new Cookie("email", adress);
	    userCookie.setMaxAge(30 * 60);
	    response.addCookie(userCookie);
		UtilisateurService u = new UtilisateurService();
		Utilisateur utilisateur =new Utilisateur(firstname,lastename,adress,pwd);
		u.saveUtilisateur(utilisateur);
		response.sendRedirect("index.jsp");
	}
	}
