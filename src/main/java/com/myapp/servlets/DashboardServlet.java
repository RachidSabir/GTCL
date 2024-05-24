package com.myapp.servlets;


import com.myapp.models.Utilisateur;
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


@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UtilisateurService utilisateurService;
    private TacheService tacheService;
    private ProjetService projetService;

    public DashboardServlet() {
        this.utilisateurService = new UtilisateurService();
        this.tacheService = new TacheService();
        this.projetService = new ProjetService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Get existing session if present, do not create a new one
        if (session != null && session.getAttribute("currentUser") != null) {
            Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
            request.setAttribute("currentSection", "dashboard");
            request.setAttribute("totalProjet", projetService.totalProjet());
            request.setAttribute("totalTache", tacheService.totalTasksAssignedTo(currentUser.getId()));
            request.setAttribute("totalTacheOnHold", tacheService.totalTasksOnHold(currentUser.getId()));
            request.setAttribute("totalMembres", utilisateurService.totalMembres());
            request.setAttribute("totalChefs", utilisateurService.totalProjectManagers());
            request.setAttribute("totalNew", tacheService.totalTasksNEW(currentUser.getId()));
            request.setAttribute("totalInProgress", tacheService.totalTasksInProgress(currentUser.getId()));
            request.setAttribute("totalDone", tacheService.totalTasksDone(currentUser.getId()));
            request.getRequestDispatcher("/dashboard/Tableau_de_Bord.jsp").forward(request, response);
        }
    }
}
