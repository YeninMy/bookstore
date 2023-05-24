<#import "parts/common.ftlh" as common>
<@common.page>
    <#include "parts/security.ftlh">
    <h2>${book.name}</h2>
    <div class="image-container">
        <#if book.getDirectLinkToImage()??>
            <img src="${book.getDirectLinkToImage()}" alt="book cover image" style="height: 300px">
        <#else>
            <img src="${book.getPlaceHolder()}" alt="book cover placeholder" style="height: 300px">
        </#if>
    </div>
    <#list book.authors as author>
        <p>${author.name()}</p>
    </#list>
    <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>
    <p>${book.price}</p>

    <div class="d-flex justify-content-center">
        <form method="post" action="/cart/add-book/${book.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="bookId" value="${book.id}">
            <button class="btn btn-success" type="submit">Add to Cart</button>
        </form>
    </div>
    <p>Rating:</p>
    <#if book.averageRating() == 0.0>
        <p>Nor rated yet</p>
    <#else>
        <p>${book.averageRating()}</p>
    </#if>
    <p>Comments:</p>
    <#if book.getComments()??>
        <#list book.getComments() as comment>
            <p>${comment.person.username}</p>
            <p>${comment.text}</p>
             <#if isAdmin>
                <form action="/delete-comment" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="commentId" value="${comment.id}">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button type="submit">DeleteComment</button>
                </form>
                </#if>
        </#list>
    <#else>
    </#if>
    <form method="post" action="/rate-book/${book.id}">
        <label for="mark">Rate this book:</label><br>
        <input type="number" id="mark" name="mark" min="1" max="5" step="1" required>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Rate</button>
    </form>

    <form method="post" action="/new-comment/${book.id}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <textarea name="comment" rows="4" cols="50" placeholder="Write your comment here"></textarea>
        <button type="submit" class="btn btn-primary">Post comment</button>
    </form>

    <h2>More books in genre:${book.genre.getDisplayName()}</h2>
    <#list books as book>
        <tr>
            <h2><a href="/books/book/${book.id}">${book.name}</a></h2>
            <div class="image-container">
                <#if book.getDirectLinkToImage()??>
                    <img src="${book.getDirectLinkToImage()}" alt="book cover image" style="height: 300px">
                <#else>
                    <img src="${book.getPlaceHolder()}" alt="book cover placeholder" style="height: 300px">
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