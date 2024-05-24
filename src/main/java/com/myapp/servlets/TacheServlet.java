package com.myapp.servlets;

import com.myapp.models.*;
import com.myapp.service.ProjetService;
import com.myapp.service.TacheService;
import com.myapp.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/TacheServlet")
public class TacheServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TacheService tacheService;
    private UtilisateurService utilisateurService;

    private ProjetService projetService;

    public TacheServlet() {
        projetService = new ProjetService();
        tacheService = new TacheService();
        utilisateurService = new UtilisateurService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        HttpSession session = request.getSession(false); // Get existing session if present, do not create a new one
        if (session != null && session.getAttribute("currentUser") != null) {
            Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
            if (Objects.nonNull(id)) {
                List<Projet> projets = new ArrayList<>();
                if (currentUser instanceof ChefProjet) {
                    projets = projetService.getListProjectManagedBy(currentUser.getId());
                } else {
                    projets = projetService.getProjets();
                }
                List<Membre> membres = utilisateurService.getMembres();
                Tache tache = tacheService.getTacheById(Integer.parseInt(id));
                request.setAttribute("membres", membres);
                request.setAttribute("tache", tache);
                request.setAttribute("projets", projets);
                request.setAttribute("currentSection", "tache");

                request.getRequestDispatcher("/tache/updateTask.jsp").forward(request, response);
            } else if ("addTask".equals(action)) {
                List<Membre> membres = utilisateurService.getMembres();
                request.setAttribute("membres", membres);
                List<Projet> projets = new ArrayList<>();
                if (currentUser instanceof ChefProjet) {
                    projets = projetService.getListProjectManagedBy(currentUser.getId());
                } else {
                    projets = projetService.getProjets();
                }
                request.setAttribute("projets", projets);
                request.setAttribute("currentSection", "tache");

                request.getRequestDispatcher("/tache/addTask.jsp").forward(request, response);
            } else {

                List<Tache> taches;
                if (currentUser instanceof Admin) {
                    taches = tacheService.getAllTaches();
                } else if (currentUser instanceof ChefProjet) {
                    taches = tacheService.getTachesByCreator(currentUser.getId());
                } else {
                    taches = tacheService.getTachesByAssignee(currentUser.getId());
                }
                request.setAttribute("taches", taches);
                request.setAttribute("currentSection", "tache");
                request.getRequestDispatcher("/tache/Task.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/Athentification.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            doPut(request, response);
        } else {
            HttpSession session = request.getSession(false); // Get existing session if present, do not create a new one
            if (session != null && session.getAttribute("currentUser") != null) {
                Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
                String tacheTitre = request.getParameter("tacheTitre");
                String tacheDescription = request.getParameter("tacheDescription");
                String email = request.getParameter("assignedTo");
                String projetId = request.getParameter("projet");

                UtilisateurService utilisateurService = new UtilisateurService();
                Utilisateur assignedTo = utilisateurService.getUtilisateurByEmail(email);
                Projet projet = projetService.getById(Integer.valueOf(projetId));

                Tache newTache = new Tache(tacheTitre, tacheDescription, currentUser, (Membre) assignedTo);
                newTache.setProject(projet);
                tacheService.saveTache(newTache);


                response.sendRedirect(request.getContextPath() + "/TacheServlet");
            } else {
                response.sendRedirect("/Athentification.jsp");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tacheIdString = request.getParameter("id");
        if (tacheIdString != null && !tacheIdString.isEmpty()) {
            int tacheId = Integer.parseInt(tacheIdString);
            String updatedTacheTitre = request.getParameter("tacheTitre");
            String updatedTacheDescription = request.getParameter("tacheDescription");
            String assignedToEmail = request.getParameter("assignedTo");
            TaskStatus updatedStatus = TaskStatus.valueOf(request.getParameter("status"));

            UtilisateurService utilisateurService = new UtilisateurService();
            Utilisateur assignedTo = utilisateurService.getUtilisateurByEmail(assignedToEmail);

            Tache updatedTache = tacheService.getTacheById(tacheId);
            if (updatedTache != null) {
                updatedTache.setTitre(updatedTacheTitre);
                updatedTache.setDescription(updatedTacheDescription);
                updatedTache.setStatus(updatedStatus);
                updatedTache.setAssignedTo((Membre) assignedTo);

                tacheService.updateTache(updatedTache);
            }
        }
        response.sendRedirect(request.getContextPath() + "/TacheServlet");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tacheId = Integer.parseInt(request.getParameter("id"));
        tacheService.deleteTache(tacheId);
        // Respond with success message
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\":\"Project deleted successfully\"}");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
