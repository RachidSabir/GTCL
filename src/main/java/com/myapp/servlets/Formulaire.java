package com.myapp.servlets;


import com.myapp.models.Membre;
import com.myapp.models.Utilisateur;
import com.myapp.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

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
        Utilisateur utilisateur = new Membre(firstname, lastename, adress, pwd);
        u.saveUtilisateur(utilisateur);
        utilisateur = u.getUtilisateurByEmail(adress);

        HttpSession session = request.getSession();
        session.setAttribute("currentUser", utilisateur);
        // Rediriger vers la page de tableau de board
        response.sendRedirect(request.getContextPath() + "/DashboardServlet");
    }
}
