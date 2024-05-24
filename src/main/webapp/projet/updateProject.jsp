<%@ page import="com.myapp.models.ChefProjet" %>
<%@ page import="com.myapp.models.Projet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>
<%
    Projet projet = (Projet) request.getAttribute("projet");
%>
<div class="main-content">
    <h3 class="h3"> Modifier le projet <%= projet.getTitre() %>
    </h3>
    <form action="ProjectServlet" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= projet.getId() %>">
        <div class="mb-3">
            <label for="Titre" class="form-label">Nom du projet</label>
            <input value="<%= projet.getTitre() %>" type="text" class="form-control" id="Titre" name="Titre"
                   aria-describedby="Nom du projet">
        </div>
        <div class="mb-3">
            <label for="Description" class="form-label">Description</label>
            <input value="<%= projet.getDescription() %>" type="text" class="form-control" id="Description"
                   name="Description"
                   aria-describedby="Décrire le projet">
        </div>
        <div class="mb-3">
            <label class="form-label" for="chefProjet"><strong>Assigné à:</strong></label>
            <select class="form-control" name="chefProjet" id="chefProjet" required>
                <option>Veuillez définir le chef de projet</option>
                <%
                    List<ChefProjet> chefs = (List<ChefProjet>) request.getAttribute("chefs");
                    for (ChefProjet utilisateur : chefs) {
                %>
                <option value="<%= utilisateur.getEmail() %>" <%= utilisateur.getEmail().equals(projet.getChef().getEmail()) ? "selected" : "" %>><%= utilisateur.getFirstName() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Modifier</button>
    </form>
</div>

<%@ include file="../footer/footer.jsp" %>