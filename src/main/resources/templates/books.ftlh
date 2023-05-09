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
            <p>${book.author.name}</p>
            <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>
        </tr>
    </#list>

</@common.page>
