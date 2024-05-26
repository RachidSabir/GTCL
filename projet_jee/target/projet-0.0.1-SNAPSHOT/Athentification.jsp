<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="form.css">
    <title>Projet JEE</title>
</head>

<body>

<div class="container" id="container">
    <div class="form-container sign-up">
        <form action="Formulaire" method="post">
            <h1>Inscription</h1>
            <input type="text" placeholder="First Name" name="firstname">
            <input type="text" placeholder="Last Name" name="lastname">
            <input type="email" placeholder="Email" name="adress">
            <input type="password" placeholder="Password" name="pwd">
            <button>S'inscrire</button>
        </form>
    </div>
    <div class="form-container sign-in">
        <form action="AuthentificationServlet" method="post">
            <h1>Se connecter</h1>
            <%if (request.getAttribute("error") != null) {%>
            <span>Invalid email or password!</span>
            <%} %>
            <input type="email" placeholder="Email" name="email">
            <input type="password" placeholder="Password" name="pwd">
            <a href="#">Oublié mot de passe?</a>
            <button>Se connecter</button>
        </form>
    </div>
    <div class="toggle-container">
        <div class="toggle">
            <div class="toggle-panel toggle-left">
                <h1>Connexion</h1>
                <p>Vous avez déja un compte!</p>
                <button class="hidden" id="login">Se connecter</button>
            </div>
            <div class="toggle-panel toggle-right">
                <h1>Inscription</h1>
                <p>Inscrivez-vous pour utiliser toutes les fonctionnalitées du site</p>
                <button class="hidden" id="register">S'inscrire</button>
            </div>
        </div>
    </div>
</div>

<script src="form.js"></script>
</body>

</html>