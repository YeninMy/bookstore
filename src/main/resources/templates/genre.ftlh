<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>Books by Genre: ${selectedGenre}</h1>
    <br>
    <#list books as book>
        <tr>
            <h2><a href="/books/book/${book.id}">${book.name}</a></h2>
            <div class="image-container">
                <#if book.coverImage?has_content>
                    <img src="/images/${book.getCoverImage()}" alt="book cover image" style="height: 300px">
                <#else>
                    <img src="/images/placeholder-image.jpg" alt="placeholder book cover image" style="height: 300px">
                </#if>
            </div>
            <#list book.authors as author>
                <p>${author.name()}</p>
            </#list>
        </tr>
    </#list>

    <br>
    <a href="/" class="btn btn-primary">Back to All Books</a>
</@common.page>