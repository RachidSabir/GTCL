<%@ page import="com.myapp.models.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myapp.models.Membre" %>
<%@ page import="com.myapp.models.Projet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>

<div class="main-content">
    <h3 class="h3"> Creer une nouvelle Tache</h3>
    <form action="<%= request.getContextPath() %>/TacheServlet" method="post">
        <div class="mb-3">
            <label class="form-label" for="tacheTitre"><strong>Titre:</strong></label>
            <input class="form-control" type="text" name="tacheTitre" id="tacheTitre" placeholder="Titre" required>
        </div>
        <br>
        <div class="mb-3">
            <label class="form-label" for="tacheDescription"><strong>Description:</strong></label>
            <input class="form-control" type="text" name="tacheDescription" id="tacheDescription"
                   placeholder="Description"
                   required>
        </div>
        <br>
        <div class="mb-3">
            <label class="form-label" for="assignedTo"><strong>Assigné à:</strong></label>
            <select class="form-control" name="assignedTo" id="assignedTo" required>
                <option>Veuillez assigné la tache à un utilisateur</option>
                <%
                    List<Membre> membres = (List<Membre>) request.getAttribute("membres");
                    for (Utilisateur utilisateur : membres) {
                %>
                <option value="<%= utilisateur.getEmail() %>"><%= utilisateur.getFirstName() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>
        <br>
        <div class="mb-3">
            <label class="form-label" for="projet"><strong>Assigné au Projet:</strong></label>
            <select class="form-control" name="projet" id="projet" required>
                <option>Veuillez assigné la tache à un projet</option>
                <%
                    List<Projet> projets = (List<Projet>) request.getAttribute("projets");
                    for (Projet projet : projets) {
                %>
                <option value="<%= projet.getId() %>"><%= projet.getTitre() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>
        <br>
        <button class="btn btn-primary" type="submit">Ajouter</button>
    </form>
</div>

<%@ include file="../footer/footer.jsp" %>
