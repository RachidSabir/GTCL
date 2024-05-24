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
    
