package com.myapp.servlets;

import com.myapp.models.ChefProjet;
import com.myapp.models.Membre;
import com.myapp.models.Utilisateur;
import com.myapp.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuthentificationServlet() {
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        UtilisateurService utilservice = new UtilisateurService();

        if (utilservice.valideUtilisateur(email, pwd)) {

            Utilisateur utilisateur = utilservice.getUtilisateurByEmail(email);

            HttpSession session = request.getSession();
            session.setAttribute("currentUser", utilisateur);

            Cookie loginCookie = new Cookie("email", email);
            loginCookie.setMaxAge(30 * 60);
            response.addCookie(loginCookie);
            Cookie roleCookie;
            if (utilisateur instanceof Membre) {
                roleCookie = new Cookie("role", "MEMBRE");
            } else if (utilisateur instanceof ChefProjet) {
                roleCookie = new Cookie("role", "CHEF_PROJET");
            } else {
                roleCookie = new Cookie("role", "ADMIN");
            }

            loginCookie.setMaxAge(30 * 60);
            response.addCookie(roleCookie);

            // Rediriger vers la page de tableau de board
            response.sendRedirect(request.getContextPath() + "/DashboardServlet");
        } else {
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("/Athentification.jsp").forward(request, response);
        }
    }
}
