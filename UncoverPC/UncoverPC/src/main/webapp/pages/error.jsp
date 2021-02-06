<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UncoverPC</title>

<!-- CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="styles.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body style="background-color: wheat;">

	<!--NavigationBar-->
	<section id="nav-bar">
		<nav class="navbar navbar-expand-lg navbar-light navbar-size">
			<a class="navbar-brand" href="index.html"><img
				src="assets/Logo1.png"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<li class="nav-item"><a class="nav-link NavBar search"
				href="/search">Search</a></li>
			<li class="nav-item"><a class="nav-link NavBar error"
				href="index.html">Home</a></li>
		</nav>
	</section>

	<!-- Errors -->
	<div>

		<h1 class="indexFont errorTitle">Something went wrong :/</h1>

		<div class="errorText">
			<br>
			<b>${errorType}: </b>${errorInfo}</br> <br>
			<b>Solution: </b>${solution}</br>
		</div>
	</div>

</body>

</html>