<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myapp.models.Tache" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>

<div class="main-content">
    <h2 class="h2">Gestion des taches</h2>
    <p class="lead">Gérez vos taches à partir d'ici. Ajoutez, modifiez ou supprimez les taches selon vos besoins.</p>

    <% if (!"MEMBRE".equals(userRole)) { %>
    <!-- Button to add a new project with icon -->
    <a href="${pageContext.request.contextPath}/TacheServlet?action=addTask" class="btn btn-primary">
        <i class="bx bx-plus-circle"></i> Ajouter Tache
    </a>
    <%}%>
    <div style="margin:50px ;	display: flex ; flex-wrap: wrap ; justify-content: space-between ;">
        <%
            List<Tache> taches = (List<Tache>) request.getAttribute("taches");
            if (taches != null && !taches.isEmpty()) {
                for (Tache tache : taches) {
        %>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Titre: <%= tache.getTitre() %>
                </h5>
                <h6 class="card-subtitle mb-2 text-muted"><%= tache.getStatus() %>
                </h6>
                <p class="card-text">
                    Description: <%= tache.getDescription() %>
                    <br>
                    lié au projet : <%= tache.getProject().getTitre() %>
                </p>
                <pre><i class='bx bx-user-check'></i><strong>cree par : <%= tache.getCreatedBy().getFirstName() %></strong>
					<i class='bx bx-user'></i><strong>Assigne à : <%= tache.getAssignedTo().getFirstName() %></strong> </pre>
                <% if (!"MEMBRE".equals(userRole)) { %>
                <button class="btn btn-warning"><a
                        href="${pageContext.request.contextPath}/TacheServlet?id=<%= tache.getId() %>"><i
                        class="bx bx-edit"></i> Modifier</a></button>
                <button onclick="deleteTask(<%=tache.getId()%>);" class="btn btn-danger"><i class="bx bx-trash"></i>
                    Supprimer
                </button>
                <% } %>
            </div>
        </div>
        <% }
        } %>
    </div>
</div>

<div class="toast-container position-fixed top-0 end-0 p-3">
    <div id="myToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <strong class="me-auto">Notification</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
        </div>
    </div>
</div>

<%@ include file="../footer/footer.jsp" %>
