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
    <title>My Work Station</title>
</head>

<body>
	<%
		if (session.getAttribute("usernameLogin") == null) {
			response.sendRedirect("index.jsp");
		}
	%>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <a class="navbar-brand" href="#"><b>Reliable Bank</b></a>
        <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#collapsibleNavId"
            aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation"></button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavId">
            <span class="hello">Hello, <a name="username" class="active" href=""><em>${usernameLogin} </em></a>
                <button type="button" class="btn btn-sm px-0" name="setting">
                    <i class="fa fa-cog"></i>
                </button>
            </span>
            <form action="logoff">
            	<button type="submit" class="btn btn-sm px-0" style="color:white; font-style: italic;">&nbsp;Sign out</button>
            </form>
        </div>
    </nav>

    <div class="container">
        <div class="row" style="background-color: darkgrey">
            <div class="col-sm" style="max-width: 280px">
                <div>
                    <button onclick="refreshPage()" type="submit" class="btn btn-emp">My work station</button>
                </div>
                <div>
                    <button onclick="getAccounts()" type="submit" class="btn btn-emp">View customer account</button>
                </div>
                <div>
                    <button onclick="getPendingAccounts()" type="submit" class="btn btn-emp">Pending user account</button>
                </div>
                <div>
                    <button onclick="getPendingCards()" type="submit" class="btn btn-emp">Pending customer account</button>
                </div>
                <div>
                    <button onclick="getlog()" type="submit" class="btn btn-emp">View all transactions</button>
                </div>
            </div>

            <div class="col-sm">
                <div id="empRespond"><h1>${usernameLogin}, </h1></div>
            </div>
        </div>
    </div>

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
    <script type="text/javascript" src="scripts/emp.js"></script>
</body>

<footer>
    <nav class="navbar navbar-expand text-light navbar-dark bg-dark fixed-bottom">
        <div class="navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <small>Reliable Bank © 2020</small>
                </li>
            </ul>
        </div>
    </nav>
</footer>

</html>