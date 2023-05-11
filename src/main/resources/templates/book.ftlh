<#import "parts/common.ftlh" as common>
<@common.page>

    <h2>${book.name}</h2>
    <div class="image-container">
        <#if book.coverImage?has_content>
            <img src="/images/${book.getCoverImage()}" alt="book cover image" style="height: 300px">
        <#else>
            <img src="/images/placeholder-image.jpg" alt="Заглушка обложки книги" style="height: 300px">
        </#if>
    </div>
    <#list book.authors as author>
        <p>${author.name()}</p>
    </#list>
    <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>
    <p>${book.price}</p>

    <form method="post" action="/cart/add/${book.id}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="bookId" value="${book.id}">
        <button type="submit" class="btn btn-primary">Add to cart</button>
    </form>
    <h2>More books in genre:${book.genre}</h2>
    <#list books as book>
        <tr>
            <h2><a href="/books/book/${book.id}">${book.name}</a></h2>
            <div class="image-container">
                <#if book.coverImage?has_content>
                    <img src="/images/${book.getCoverImage()}" alt="book cover image" style="height: 300px">
                <#else>
                    <img src="/images/placeholder-image.jpg" alt="Заглушка обложки книги" style="height: 300px">
                </#if>
            </div>
            <#list book.authors as author>
                <p>${author.name()}</p>
            </#list>
            <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>
            <p>${book.price}</p>
        </tr>
    </#list>
    <br>
    <a href="/" class="btn btn-primary">Back to All Books</a>

</@common.page>