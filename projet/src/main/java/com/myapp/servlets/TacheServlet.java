package com.myapp.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.myapp.models.Tache;
import com.myapp.models.TacheService;

@WebServlet("/TacheServlet")
public class TacheServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TacheService tacheService;

    public TacheServlet() {
        tacheService = new TacheService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tache> taches = tacheService.getTache();
        
        request.setAttribute("taches", taches);
        request.getRequestDispatcher("/listTaches.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String method = request.getParameter("_method");

        if ("put".equalsIgnoreCase(method)) {
            doPut(request, response);
        } else {
        	String tacheTitre = request.getParameter("tacheTitre");
        	String tacheDescription = request.getParameter("tacheDescription");
        	if(tacheTitre != null && !tacheTitre.isEmpty()) {
        		Tache newTache = new Tache(tacheTitre, tacheDescription);
        		tacheService.saveTache(newTache);
        	}
        	response.sendRedirect(request.getContextPath() + "/TacheServlet");
        	
        }
    }
    
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String tacheIdString = request.getParameter("tacheId");
        if (tacheIdString != null && !tacheIdString.isEmpty()) {
            int tacheId = Integer.parseInt(tacheIdString);
            String updatedTacheTitre = request.getParameter("tacheTitre");
            String updatedTacheDescription = request.getParameter("tacheDescription");

            Tache updatedTache = tacheService.getTacheById(tacheId);
            if (updatedTache != null) {
                updatedTache.setTitre(updatedTacheTitre);
                updatedTache.setDescription(updatedTacheDescription);
                tacheService.updateTache(updatedTache);
            }
        } else {
            // Gérer le cas où l'ID de la tâche est manquant ou vide
            // Vous pouvez renvoyer une erreur ou effectuer une autre action appropriée
        }
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tacheId = Integer.parseInt(request.getParameter("tacheId"));
        tacheService.deleteTache(tacheId);
        doGet(request, response);
    }
}
