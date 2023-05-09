<#import "parts/common.ftlh" as common>
<@common.page>
<h1>MY</h1>
<h2>bookstore</h2>

<form class="d-flex" role="search">
    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success" type="submit">Search</button>
</form>

<#if person??>
    <h1>Welcome, ${person.username}, Book for you!!!</h1>

<#else>
    <h1>Welcome, guest!</h1>
</#if>
<#--<h2>Add a new book</h2>-->
<#--<form action="/add-book" method="post">-->
<#--    <label for="name">Name:</label>-->
<#--    <input type="text" name="name" id="name" required><br>-->

<#--    <button type="submit">Add book</button>-->
<#--</form>-->
<div id="random-book">
    <h2><a href="/books/book/${book.id}">${book.name}</a></h2>
    <div class="image-container">
        <#if book.coverImage?has_content>
            <img src="/images/${book.getCoverImage()}" alt="book cover image" style="height: 300px">
        <#else>
            <img src="/images/placeholder-image.jpg" alt="Заглушка обложки книги" style="height: 300px">
        </#if>
    </div>
    <p>${book.author.name}</p>
    <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>

    <button onclick="location.reload()" id="update-book">Update book</button>
</div>
</@common.page>