<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Personal CSS -->
    <link rel="stylesheet" href="css/styles.css">
    <title>Reliable Java Bank</title>
</head>

<body>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <a class="navbar-brand" href="http://localhost:2222/p1_reliable_bank/index.jsp"><b>Reliable Bank</b></a>
        <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#collapsibleNavId"
            aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation"></button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavId">
            <form action="login" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" name="usernameLogin" type="text" placeholder="username" size="12"
                    required>
                <input class="form-control mr-sm-2" name="passwordLogin" type="password" placeholder="password" size="12"
                    required>
                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Log in</button>
            </form>
        </div>
    </nav>

    <form action="signup" method="POST">
        <div class="container">
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <b>Fill in this form to create an account</b>
                </div>
            </div>
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <input type="text" class="form-control" name="fullname" placeholder="full name" size="26" required>
                </div>
            </div>
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <input type="email" class="form-control" name="email" placeholder="email" required>
                </div>
            </div>
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <input type="text" class="form-control" name="usernameSignup" placeholder="username" required>
                </div>
            </div>
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <input type="password" class="form-control" id="password1Signup" placeholder="password" required>
                </div>
            </div>
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <input onkeyup="validatePassword()" type="password" class="form-control" id="password2Signup"
                        placeholder="confirm password" required>
                </div>
            </div>
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="checkbox" required>
                        <label class="form-check-label"> Agreed to the Terms of Use </label>
                    </div>
                </div>
            </div>
            <div class="row justify-content-end mr-5">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" id="signupbtn">Sign Up</button>
                </div>
            </div>
        </div>
    </form>

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <!--Personal JS-->
    <script type="text/javascript" src="scripts/main.js"></script>
</body>

<footer>
    <nav class="navbar navbar-expand text-light navbar-dark bg-dark fixed-bottom">
        <div class="navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a><small>Reliable Bank © 2020</small></a>
                </li>
            </ul>
        </div>
    </nav>
</footer>

</html>