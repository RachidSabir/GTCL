<%@ page import="java.util.List" %>
<%@ page import="com.myapp.models.Projet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>

<div class="main-content">
    <h2 class="h2">Gestion de projet</h2>
    <p class="lead">Gérez vos projets à partir d'ici. Ajoutez, modifiez ou supprimez des projets selon vos besoins.</p>

    <!-- Button to add a new project with icon -->
    <a href="${pageContext.request.contextPath}/ProjectServlet?action=addProject" class="btn btn-primary">
        <i class="bx bx-plus-circle"></i> Ajouter Projet
    </a>

    <!-- Projects Table -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Projet</th>
            <th>Nom Projet</th>
            <th>description</th>
            <th>Chef du projet</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Projet> projects = (List<Projet>) request.getAttribute("projets");

            if (projects != null && !projects.isEmpty()) {
                for (Projet project : projects) {
        %>

        <tr>
            <td><%=project.getId()%>
            </td>
            <td><%=project.getTitre()%>
            </td>
            <td><%=project.getDescription()%>
            </td>
            <td><%=project.getChef().getFirstName().concat(" ").concat(project.getChef().getLastName())%>
            </td>
            <td>
                <button class="btn btn-warning"><a
                        href="${pageContext.request.contextPath}/ProjectServlet?id=<%= project.getId() %>"><i
                        class="bx bx-edit"></i> Modifier</a></button>
                <button onclick="deleteItem(<%=project.getId()%>);" class="btn btn-danger"><i class="bx bx-trash"></i>
                    Supprimer
                </button>
            </td>
        </tr>

        <% }
        } else {
        %>

        <tr>
            <td colspan="6"> y'a aucun projet cree !</td>
        </tr>
        <%}%>
        </tbody>
    </table>
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
