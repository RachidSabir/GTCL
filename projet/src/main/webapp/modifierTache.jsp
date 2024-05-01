<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myapp.models.Tache" %>
<%@ page import="com.myapp.models.TacheService" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier une Tâche</title>
</head>
<body>
    <h1>Modifier une Tache:</h1>

    <form action="<%= request.getContextPath() %>/TacheServlet" method="post">
	    <input type="hidden" name="_method" value="put">
	    Nouveau titre de la tâche: <input type="text" id="tacheTitre" name="tacheTitre" value="<%= request.getParameter("tacheTitre") %>" required><br>
	    Nouvelle description de la tâche: <input type="text" id="tacheDescription" name="tacheDescription" value="<%= request.getParameter("tacheDescription") %>" required><br>
	    <input type="submit" value="Enregistrer">
	    <button type="button" id="cancelButton">Annuler</button>
	</form>


	<script>
        document.getElementById('cancelButton').addEventListener('click', function() {
            window.location.href = '<%= request.getContextPath() %>/TacheServlet';
        });
    </script>

</body>
</html>
