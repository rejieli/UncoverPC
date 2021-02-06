<!DOCTYPE html>
<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="refresh" content="1;url=/result?id=${id}" />
<script src="jquery-2.1.4.js"></script>
<link rel="stylesheet" type="text/css" href="animations.css" />
</head>

<body>
	<div class="loader-wrapper">
		<span class="title" id="text">Building your computer...</span>
		<script>
			var counter = 0;
			setInterval(
					function() {
						++counter;
						document.getElementById("text").innerHTML = "Building your computer..."
								+ counter;
					}, 1000);
		</script>
		<span class="loader"><span class="loader-inner"></span></span>
	</div>
</body>

</html>