<!DOCTYPE html>
<html xmlns:ftl="http://www.freemarker.org">
<head>
    <title>My Book Collection</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>
<h1>My Book Collection</h1>
<br>
<a href="/registration" class="btn btn-primary">Registration</a>
<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
    </tr>
    </thead>
    <tbody>
    <#list books as book>
        <tr>
            <td>${book.name}</td>
            <td>${book.author.name}</td>
            <td><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></td>
        </tr>
    </#list>
    </tbody>
</table>

</body>
</html>
