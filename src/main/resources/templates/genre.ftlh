<!DOCTYPE html>
<html xmlns:ftl="http://www.freemarker.org">
<head>
    <title>Books by Genre</title>
</head>
<body>
<h1>Books by Genre: ${selectedGenre}</h1>
<br>
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
            <td>${book.genre.displayName}</td>
        </tr>
    </#list>
    </tbody>
</table>
<br>
<a href="/" class="btn btn-primary">Back to All Books</a>
</body>
</html>