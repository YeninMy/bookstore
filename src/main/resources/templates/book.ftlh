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
            <p>${book.author.name}</p>
            <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>

<br>
<a href="/" class="btn btn-primary">Back to All Books</a>
</@common.page>