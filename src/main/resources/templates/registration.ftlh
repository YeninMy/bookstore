<!DOCTYPE html>
<html xmlns:ftl="http://www.freemarker.org">
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">MYBookstore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/books">Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/basket">Registration</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/profile" role="button" data-bs-toggle="dropdown" aria-expanded="false">Profile</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/profile">Profile</a></li>
                        <li><a class="dropdown-item" href="#">Logout</a></li>
                        <li><hr class="dropdown-divider"></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h2>Registration form</h2>
<form class="rf" method="post" action="/registration">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username"><br>
    <#if errorUsername??>
        <div class="invalid-feedback">
            ${errorUsername}
        </div>
    </#if>
    <label for="email">Email:</label>
    <input type="text" name="email" id="email"><br>
    <#if errorEmail??>
        <div class="invalid-feedback">
            ${errorEmail}
        </div>
    </#if>
    <label for="password">Username:</label>
    <input type="text" name="password" id="password"><br>

    <input type="submit" value="Add">
</form>
<br>
<a href="/" class="btn btn-primary">Books list</a>
</body>
</html>