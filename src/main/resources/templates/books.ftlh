<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>My Book Collection</h1>
    <br>
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
            <form method="post" action="/cart/add/${book.id}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="bookId" value="${book.id}">
                <button type="submit" class="btn btn-primary">Add to cart</button>
            </form>
        </tr>
    </#list>

</@common.page>
