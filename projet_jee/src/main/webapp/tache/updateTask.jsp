<%@ page import="java.util.List" %>
<%@ page import="com.myapp.models.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>
<%
    Tache tache = (Tache) request.getAttribute("tache");
%>
<div class="main-content">
    <h3 class="h3">Modifier la Tache <%= tache.getTitre() %>
    </h3>
    <form action="<%= request.getContextPath() %>/TacheServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= tache.getId() %>">
        <div class="mb-3">
            <label class="form-label" for="tacheTitre"><strong>Titre:</strong></label>
            <input class="form-control" type="text" name="tacheTitre" id="tacheTitre" value="<%= tache.getTitre() %>"
                   placeholder="Titre"
                   required>
        </div>
        <br>
        <div class="mb-3">
            <label class="form-label" for="tacheDescription"><strong>Description:</strong></label>
            <input value="<%= tache.getDescription() %>" class="form-control" type="text" name="tacheDescription"
                   id="tacheDescription" placeholder="Description"
                   required>
        </div>
        <br>
        <div class="mb-3">
            <label class="form-label" for="status"><strong>Status </strong></label>
            <select class="form-control" name="status" id="status" required>
                <option disabled selected>Veuillez choisir un status</option>

                <option value="<%= TaskStatus.NEW %>" <%= TaskStatus.NEW.equals(tache.getStatus()) ? "selected" : "" %>>
                    Nouveau
                </option>
                <option value="<%= TaskStatus.IN_PROGRESS %>" <%= TaskStatus.IN_PROGRESS.equals(tache.getStatus()) ? "selected" : "" %>>
                    En cours
                </option>
                <option value="<%= TaskStatus.DONE %>" <%= TaskStatus.DONE.equals(tache.getStatus()) ? "selected" : "" %>>
                    Terminé
                </option>

            </select>
        </div>
        <br>
        <div class="mb-3">
            <label class="form-label" for="assignedTo"><strong>Assigné à:</strong></label>
            <select class="form-control" name="assignedTo" id="assignedTo" required>
                <option disabled selected>Veuillez assigné la tache à un utilisateur</option>
                <%
                    List<Membre> membres = (List<Membre>) request.getAttribute("membres");
                    for (Utilisateur utilisateur : membres) {
                %>
                <option value="<%= utilisateur.getEmail() %>" <%= utilisateur.getEmail().equals(tache.getAssignedTo().getEmail()) ? "selected" : "" %>><%= utilisateur.getFirstName() %>
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
                <option disabled selected>Veuillez assigné la tache à un projet</option>
                <%
                    List<Projet> projets = (List<Projet>) request.getAttribute("projets");
                    for (Projet projet : projets) {
                %>
                <option value="<%= projet.getId() %>" <%= projet.getId() == tache.getProject().getId() ? "selected" : "" %>><%= projet.getTitre() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>
        <br>
        <button class="btn btn-primary" type="submit">Modifier</button>
    </form>
</div>

<%@ include file="../footer/footer.jsp" %>
