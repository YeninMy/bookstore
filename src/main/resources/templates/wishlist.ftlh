<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>Wishlist</h1>
    <#if books?has_content>


        <#list books as book>
            <tr>
                <td>${book.name}</td>
                <#list book.authors as author>
                    <p>${author.name()}</p>
                </#list>
                <td>${book.price}</td>
                <form method="post" action="/wishlist/cart/add-book/${book.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button class="btn btn-success" type="submit">Add to Cart</button>
                </form>
                <form method="post" action="/wishlist/remove/${book.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button type="submit" class="btn btn-primary">Remove</button>
                </form>
            </tr>
        </#list>



    <#else>
        <p>Empty yet</p>
    </#if>
    <a href="/books" class="btn btn-primary">Return to books</a>



</@common.page>