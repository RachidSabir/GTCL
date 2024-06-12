<%@ page import="com.myapp.models.ChefProjet" %>
<%@ page import="com.myapp.models.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>
<%
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
%>
<div class="main-content">
    <h3 class="h3"> Modifier les informations de
        l'utilisateur <%= utilisateur.getFirstName().concat(" ").concat(utilisateur.getLastName()) %>
    </h3>
    <form action="UtilisateurServlet" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= utilisateur.getId() %>">
        <div class="mb-3">
            <label for="lastName" class="form-label">Nom de l'utilisateur</label>
            <input value="<%= utilisateur.getLastName() %>" type="text" class="form-control" id="lastName"
                   name="lastName"
                   aria-describedby="Nom de l'utilisateur">
        </div>
        <br>
        <div class="mb-3">
            <label for="firstName" class="form-label">Prénom de l'utilisateur</label>
            <input value="<%= utilisateur.getFirstName() %>" type="text" class="form-control" id="firstName"
                   name="firstName"
                   aria-describedby="Prénom de l'utilisateur">
        </div>
        <br>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input value="<%= utilisateur.getEmail() %>" class="form-control" type="email" id="email" name="email"
                   aria-describedby="Email">
        </div>
        <br>
        <div class="mb-3">
            <label for="pwd" class="form-label">Mot de passe</label>
            <input value="<%= utilisateur.getPassword() %>" class="form-control" type="password" name="pwd" id="pwd"
                   aria-describedby="Mot de passe">
        </div>
        <div class="mb-3">
            <label class="form-label" for="role"><strong>Role </strong></label>
            <select class="form-control" name="role" id="role" required>
                <option selected disabled>Veuillez définir le role de l'utilisateur</option>
                <option value="Admin" <%= utilisateur.getRole().equals("Admin") ? "selected" : "" %> > Admin
                </option>
                <option value="chefProjet" <%= utilisateur.getRole().equals("Chef Projet") ? "selected" : "" %>> Chef de
                    projet
                </option>
                <option value="Membre" <%= utilisateur.getRole().equals("Membre") ? "selected" : "" %>> Membre de
                    l'équipe
                </option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Modifier</button>
    </form>
</div>

<%@ include file="../footer/footer.jsp" %>
