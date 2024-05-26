<%@ page import="com.myapp.models.ChefProjet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../sideBar/sidebar.jsp" %>
<%@ include file="../header/header.jsp" %>

<div class="main-content">
    <h3 class="h3"> Ajouter un nouveau utilisateur</h3>
    <form action="UtilisateurServlet" method="POST">
        <div class="mb-3">
            <label for="lastName" class="form-label">Nom de l'utilisateur</label>
            <input type="text" class="form-control" id="lastName" name="lastName"
                   aria-describedby="Nom de l'utilisateur">
        </div>
        <br>
        <div class="mb-3">
            <label for="firstName" class="form-label">Prénom de l'utilisateur</label>
            <input type="text" class="form-control" id="firstName" name="firstName"
                   aria-describedby="Prénom de l'utilisateur">
        </div>
        <br>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input class="form-control" type="email" id="email" name="email"
                   aria-describedby="Email">
        </div>
        <br>
        <div class="mb-3">
            <label for="pwd" class="form-label">Mot de passe</label>
            <input class="form-control" type="password" name="pwd" id="pwd"
                   aria-describedby="Mot de passe">
        </div>
        <div class="mb-3">
            <label class="form-label" for="role"><strong>Role </strong></label>
            <select class="form-control" name="role" id="role" required>
                <option selected disabled>Veuillez définir le role de l'utilisateur</option>
                <option value="Admin"> Admin
                </option>
                <option value="chefProjet"> Chef de projet
                </option>
                <option value="Membre"> Membre de l'équipe
                </option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
</div>

<%@ include file="../footer/footer.jsp" %>