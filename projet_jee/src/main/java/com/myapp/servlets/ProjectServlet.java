package com.myapp.servlets;

import com.myapp.models.ChefProjet;
import com.myapp.models.Projet;
import com.myapp.models.Utilisateur;
import com.myapp.service.ProjetService;
import com.myapp.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjetService projetService;

    private UtilisateurService utilisateurService;

    public ProjectServlet() {
        projetService = new ProjetService();
        utilisateurService = new UtilisateurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if (Objects.nonNull(id)) {
            Projet projet = projetService.getById(Integer.parseInt(id));
            List<ChefProjet> chefs = utilisateurService.getProjectManager();
            request.setAttribute("chefs", chefs);
            request.setAttribute("projet", projet);
            request.setAttribute("currentSection", "projet");

            request.getRequestDispatcher("/projet/updateProject.jsp").forward(request, response);
        } else if ("addProject".equals(action)) {
            List<ChefProjet> chefs = utilisateurService.getProjectManager();
            request.setAttribute("chefs", chefs);
            request.setAttribute("currentSection", "projet");

            request.getRequestDispatcher("/projet/addProject.jsp").forward(request, response);
        } else {
            // Appeler la methode qui va faire l'affichage
            List<Projet> projets = projetService.getProjets();

            // Transmettre la liste des projets à la page JSP
            request.setAttribute("currentSection", "projet");
            request.setAttribute("projets", projets);

            // Rediriger vers la JSP
            request.getRequestDispatcher("/projet/Project.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            doPut(request, response);
        } else {

            ProjetService projetService = new ProjetService();
            // Récupérer les paramètres du formulaire
            String Titre = request.getParameter("Titre");
            String Description = request.getParameter("Description");
            String chefProjetMail = request.getParameter("chefProjet");
            ChefProjet chef = (ChefProjet) utilisateurService.getUtilisateurByEmail(chefProjetMail);

            // Créer un nouveau projet
            Projet nouveauProjet = new Projet(Titre, Description, chef);

            // Appeler la methode qui va faire l'ajout
            projetService.ajouterProjet(nouveauProjet);

            // Rediriger vers la page de prpjets
            response.sendRedirect(request.getContextPath() + "/ProjectServlet");

        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (Objects.nonNull(id)) {

            String Titre = request.getParameter("Titre");
            String Description = request.getParameter("Description");
            String chefProjetMail = request.getParameter("chefProjet");

            Utilisateur chefProjet = utilisateurService.getUtilisateurByEmail(chefProjetMail);

            Projet updatedProject = projetService.getById(Integer.valueOf(id));
            if (updatedProject != null) {
                updatedProject.setTitre(Titre);
                updatedProject.setDescription(Description);
                updatedProject.setChef((ChefProjet) chefProjet);

                projetService.updateProjet(updatedProject);
            }
        }
        // Rediriger vers la page de prpjets
        response.sendRedirect(request.getContextPath() + "/ProjectServlet");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the project ID from the request
        String projectId = req.getParameter("id");
        // Assume deleteProject is a method that handles the deletion logic
        boolean isDeleted = projetService.deleteProject(Integer.parseInt(projectId));

        if (isDeleted) {
            // Respond with success message
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"message\":\"Project deleted successfully\"}");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
