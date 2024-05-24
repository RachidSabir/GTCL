<%@ page import="java.util.List" %>
<%@ page import="com.myapp.models.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>

<div class="main-content">
    <h2 class="h2">Gestion des Utilisateurs</h2>
    <p class="lead">Gérez vos utilisateurs à partir d'ici. Ajoutez, modifiez ou supprimez des utilisateurs selon vos
        besoins.</p>

    <!-- Button to add a new project with icon -->
    <a href="${pageContext.request.contextPath}/UtilisateurServlet?action=addUser" class="btn btn-primary">
        <i class="bx bx-plus-circle"></i> Ajouter Utilisateur
    </a>

    <!-- Projects Table -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Utilisateur</th>
            <th>Nom Utilisateur</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");

            if (utilisateurs != null && !utilisateurs.isEmpty()) {
                for (Utilisateur utilisateur : utilisateurs) {
        %>

        <tr>
            <td><%=utilisateur.getId()%>
            </td>
            <td><%=utilisateur.getFirstName().concat(" ").concat(utilisateur.getLastName())%>
            </td>
            <td><%=utilisateur.getEmail()%>
            </td>
            <td><%=utilisateur.getRole()%>
            </td>
            <td>
                <button class="btn btn-warning"><a
                        href="${pageContext.request.contextPath}/UtilisateurServlet?id=<%= utilisateur.getId() %>"><i
                        class="bx bx-edit"></i> Modifier</a></button>
                <button onclick="deleteUser(<%=utilisateur.getId()%>);" class="btn btn-danger"><i
                        class="bx bx-trash"></i>
                    Supprimer
                </button>
            </td>
        </tr>

        <% }
        } else {
        %>

        <tr>
            <td colspan="6"> y'a aucun utilisateur cree !</td>
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
