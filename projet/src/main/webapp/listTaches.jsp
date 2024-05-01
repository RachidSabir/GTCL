<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.myapp.models.Tache" %>

   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Tâches</title>
    
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
        .hidden {
            display: none;
        }
    </style>
    
</head>
<body>
    <h1>Liste des Tâches:</h1>
    
    <button id="toggleFormButton">Ajouter une Tâche</button> <br>
    
    <div id="addTaskForm" class="hidden">
        <form id="taskForm" action="<%= request.getContextPath() %>/TacheServlet" method="post">
            <input type="text" name="tacheTitre" placeholder="Titre" required>
            <input type="text" name="tacheDescription" placeholder="Description" required><br><br>
            
            <input type="submit" value="Ajouter une Tâche">
            <button id="cancelButton" type="button">Annuler</button>
        </form>
    </div>
    
    <br>
    
    <table>
        <thead>
            <tr>
                <th>Titre</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Tache> taches = (List<Tache>) request.getAttribute("taches");
            if (taches != null) {
                for (Tache tache : taches) {
            %>
            <tr>
                <td><%= tache.getTitre() %></td>
                <td><%= tache.getDescription() %></td>
                <td>
                
                    <a href="<%= request.getContextPath() %>/modifierTache.jsp?tacheId=<%= tache.getId() %>&tacheTitre=<%= tache.getTitre() %>&tacheDescription=<%= tache.getDescription() %>">Modifier</a>

                   
                    <button onclick="deleteTache('<%= tache.getId() %>')">Supprimer</button>
                </td>
            </tr>
            <% 
                }
            }
            %>
        </tbody>
    </table>
    
  
    <script>
	    function deleteTache(tacheId) {
            if (confirm("Êtes-vous sûr de vouloir supprimer cette tâche ?")) {
                fetch('<%= request.getContextPath() %>/TacheServlet?tacheId=' + tacheId, {
                    method: 'DELETE'
                }).then(response => {
                     location.reload();
                }).catch(error => {
                    console.error('Erreur lors de la suppression de la tâche :', error);
                });
            }
        }
    </script>
    
    <script>
        document.getElementById('toggleFormButton').addEventListener('click', function() {
            var formDiv = document.getElementById('addTaskForm');
            var button = document.getElementById('toggleFormButton');
            var cancelButton = document.getElementById('cancelButton');
            
            formDiv.classList.toggle('hidden');
            button.classList.add('hidden');
            cancelButton.classList.remove('hidden');
        });
        
        document.getElementById('taskForm').addEventListener('submit', function() {
            var formDiv = document.getElementById('addTaskForm');
            var button = document.getElementById('toggleFormButton');
            var cancelButton = document.getElementById('cancelButton');
            
            formDiv.classList.add('hidden');
            button.classList.remove('hidden');
            cancelButton.classList.add('hidden');
        });
        
        document.getElementById('cancelButton').addEventListener('click', function() {
            var formDiv = document.getElementById('addTaskForm');
            var button = document.getElementById('toggleFormButton');
            var cancelButton = document.getElementById('cancelButton');
            
            formDiv.classList.add('hidden');
            button.classList.remove('hidden');
            cancelButton.classList.add('hidden');
        });
    </script>
</body>
</html>

