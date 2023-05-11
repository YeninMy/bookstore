<#import "parts/common.ftlh" as common>
<@common.page>

    <h1>Cart</h1>
    <#if books?has_content>


            <#list books as book>
                <tr>
                    <td>${book.name}</td>
                    <#list book.authors as author>
                        <p>${author.name()}</p>
                    </#list>
                    <td>${book.price}</td>
                    <form method="post" action="/cart/remove/${book.id}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="bookId" value="${book.id}">
                        <button type="submit" class="btn btn-primary">Remove</button>
                    </form>
                </tr>
            </#list>


        <br>
        <p>Total Price: ${totalPrice}</p>
        <form method="post" action="/cart/checkout">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Buy</button>
        </form>
        <br>
    <#else>
        <p>Empty yet</p>
    </#if>
    <a href="/books" class="btn btn-primary">Return to books</a>

</@common.page>