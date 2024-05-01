<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="form.css">
    <title>Modern Login Page | AsmrProg</title>
</head>

<body>

    <div class="container" id="container">
        <div class="form-container sign-up">
            <form action="Formulaire" method="post">
                <h1>Create Account</h1>
                <input type="text" placeholder="First Name" name="firstname">
                <input type="text" placeholder="Last Name" name="lastname">
                <input type="email" placeholder="Email" name="adress">
                <input type="password" placeholder="Password" name="pwd">
                <button>Sign Up</button>
            </form>
        </div>
        <div class="form-container sign-in">
            <form action="AuthentificationServlet" method="post">
                <h1>Sign In</h1>
                <input type="email" placeholder="Email" name="email">
                <input type="password" placeholder="Password" name="pwd">
                <a href="#">Forget Your Password?</a>
                <button>Sign In</button>
            </form>
        </div>
        <div class="toggle-container">
            <div class="toggle">
                <div class="toggle-panel toggle-left">
                    <h1>Welcome Back!</h1>
                    <p>Enter your personal details to use all of site features</p>
                    <button class="hidden" id="login">Sign In</button>
                </div>
                <div class="toggle-panel toggle-right">
                    <h1>Hello, Friend!</h1>
                    <p>Register with your personal details to use all of site features</p>
                    <button class="hidden" id="register">Sign Up</button>
                </div>
            </div>
        </div>
    </div>

    <script src="form.js"></script>
</body>

</html>