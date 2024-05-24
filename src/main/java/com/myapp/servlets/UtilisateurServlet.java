package com.myapp.servlets;

import com.myapp.models.Admin;
import com.myapp.models.ChefProjet;
import com.myapp.models.Membre;
import com.myapp.models.Utilisateur;
import com.myapp.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UtilisateurService utilisateurService;

    public UtilisateurServlet() {
        utilisateurService = new UtilisateurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if (Objects.nonNull(id)) {
            Utilisateur utilisateur = utilisateurService.getById(Integer.valueOf(id));
            request.setAttribute("utilisateur", utilisateur);
            request.setAttribute("currentSection", "utilisateur");

            request.getRequestDispatcher("/utilisateur/updateUtilisateur.jsp").forward(request, response);
        } else if ("addUser".equals(action)) {
            request.setAttribute("currentSection", "utilisateur");

            request.getRequestDispatcher("/utilisateur/addUtilisateur.jsp").forward(request, response);
        } else {
            // Appeler la methode qui va faire l'affichage
            List<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();

            // Transmettre la liste des projets à la page JSP
            request.setAttribute("currentSection", "utilisateur");
            request.setAttribute("utilisateurs", utilisateurs);

            // Rediriger vers la JSP
            request.getRequestDispatcher("/utilisateur/utilisateur.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            doPut(request, response);
        } else {
            String firstname = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String adress = request.getParameter("email");
            String pwd = request.getParameter("pwd");
            String role = request.getParameter("role");

            Utilisateur utilisateur;
            if ("Admin".equals(role)) {
                utilisateur = new Admin(firstname, lastName, adress, pwd);
            } else if ("chefProjet".equals(role)) {
                utilisateur = new ChefProjet(firstname, lastName, adress, pwd);
            } else {
                utilisateur = new Membre(firstname, lastName, adress, pwd);
            }

            utilisateurService.ajouterUtilisateur(utilisateur);

            // Rediriger vers la page de prpjets
            response.sendRedirect(request.getContextPath() + "/UtilisateurServlet");

        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (Objects.nonNull(id)) {

            String firstname = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String adress = request.getParameter("email");
            String pwd = request.getParameter("pwd");
            String role = request.getParameter("role");

            Utilisateur utilisateur;
            if ("Admin".equals(role)) {
                utilisateur = new Admin(firstname, lastName, adress, pwd);
            } else if ("chefProjet".equals(role)) {
                utilisateur = new ChefProjet(firstname, lastName, adress, pwd);
            } else {
                utilisateur = new Membre(firstname, lastName, adress, pwd);
            }

            utilisateurService.deleteUtilisateur(Integer.valueOf(id));
            utilisateurService.ajouterUtilisateur(utilisateur);
        }
        // Rediriger vers la page de prpjets
        response.sendRedirect(request.getContextPath() + "/UtilisateurServlet");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the project ID from the request
        String projectId = req.getParameter("id");
        // Assume deleteProject is a method that handles the deletion logic
        boolean isDeleted = utilisateurService.deleteUtilisateur(Integer.parseInt(projectId));

        if (isDeleted) {
            // Respond with success message
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"message\":\"User deleted successfully\"}");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

}