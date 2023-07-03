<!DOCTYPE html>
<html>
<head>
    <link href="/css/success.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
</head>
<body>
<div class="wrapper">
    <form class="card-body" action="/success" method="POST"></form>
    <div class="text-center">
        <h1 class="card-title" type=""><b>Success</b></h1>
        <p class="card-description"><h2>next here!</h2></p>
        <h3><button class="btn btn-outline-success" onclick="redirectToHomepage()"><b>Click here!</b></button></h3>

        <script>
            function redirectToHomepage() {
                window.location.href = "/main";
            }
        </script>

    </div>
</div>
</body>
</html>