<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <script src="jquery-2.1.4.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="result.css">
</head>

<body>

  <div class="w3-panel w3-border w3-round-large w3-hover-border-green">
    <!-- Image link -->
    <img style="float:left"; src=${imageLink};
      width="80" height="80">
    <!-- Store Logo -->
    <a target="_blank" href=${storeLink}><img style="float:right" ;
        src=${storeLogo}; width="100"
        height="100"></a>
    <p><a target="_blank"
        href=${productLink }>${productTitle}</a></p><b>${price}</b>
  </div>

</html>