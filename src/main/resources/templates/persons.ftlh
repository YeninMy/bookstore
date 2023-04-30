<!DOCTYPE html>
<html xmlns:ftl="http://www.freemarker.org">
<head>
    <title>My Book Collection</title>
</head>
<body>
<h1>Registration form</h1>
<form method="post" action="/registration">
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
    <input type="submit" value="Add">
</form>
<br>
<a href="/" class="btn btn-primary">Books list</a>
</body>
</html>