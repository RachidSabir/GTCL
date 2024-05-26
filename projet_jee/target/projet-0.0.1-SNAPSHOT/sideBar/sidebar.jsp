<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/Tab_board.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <script src="${pageContext.request.contextPath}/dashboard/scriptTableau.js"></script>
    <script src="${pageContext.request.contextPath}/projet/project.js"></script>
    <script src="${pageContext.request.contextPath}/tache/task.js"></script>
    <script src="${pageContext.request.contextPath}/utilisateur/utilisateur.js"></script>

    <title>Project & Task Management System</title>
</head>
<body>

    <%
    // Fetch the logged-in user from cookies
    Cookie[] cookies = request.getCookies();
    String userRole = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("role")) {
                userRole = cookie.getValue();
                break;
            }
        }
    }
%>
<section id="sidebar">
    <a href="#" class="brand">
        <i class='bx bxs-smile'></i>
        <span class="text">Todoly</span>
    </a>
    <ul class="side-menu top">
        <li class="<%= request.getAttribute("currentSection") != null && request.getAttribute("currentSection").equals("dashboard") ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/DashboardServlet" id="dashboard-link">
                <i class='bx bxs-dashboard'></i>
                <span class="text">Dashboard</span>
            </a>
        </li>
        <% if (!"MEMBRE".equals(userRole)) { %>
        <li class="<%= request.getAttribute("currentSection") != null &&  request.getAttribute("currentSection").equals("projet") ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/ProjectServlet" id="project-link">
                <i class='bx bxs-shopping-bag-alt'></i>
                <span class="text">Projet</span>
            </a>
        </li>
        <% } %>
        <% if (!"ADMIN".equals(userRole)) { %>
        <li class="<%= request.getAttribute("currentSection") != null &&  request.getAttribute("currentSection").equals("tache") ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/TacheServlet" id="tasks-link">
                <i class='bx bxs-doughnut-chart'></i>
                <span class="text">Taches</span>
            </a>
        </li>
        <% } %>
        <li class="<%= request.getAttribute("currentSection") != null &&  request.getAttribute("currentSection").equals("utilisateur") ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/UtilisateurServlet" id="members-link">
                <i class='bx bxs-group'></i>
                <span class="text">Utilisateurs</span>
            </a>
        </li>
    </ul>
    <ul class="side-menu">
        <li>
            <a href="${pageContext.request.contextPath}/LogoutServlet" class="logout">
                <i class='bx bxs-log-out-circle'></i>
                <span class="text">Logout</span>
            </a>
        </li>
    </ul>
</section>
