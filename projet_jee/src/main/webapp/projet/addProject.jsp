<%@ page import="com.myapp.models.ChefProjet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>

<div class="main-content">
    <form action="ProjectServlet" method="POST">
        <h3 class="h3"> Creer un nouveau projet</h3>
        <div class="mb-3">
            <label for="Titre" class="form-label">Nom du projet</label>
            <input type="text" class="form-control" id="Titre" name="Titre" aria-describedby="Nom du projet">
        </div>
        <div class="mb-3">
            <label for="Description" class="form-label">Description</label>
            <input type="text" class="form-control" id="Description" name="Description"
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
                <option value="<%= utilisateur.getEmail() %>"><%= utilisateur.getFirstName() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
</div>

<%@ include file="../footer/footer.jsp" %>
