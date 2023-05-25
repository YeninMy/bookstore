<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>Wishlist</h1>
    <#if books?has_content>
        <div class="container align-items-center">
            <#list books as book>
                <div class="col-lg-7 col-sm-12 offset-lg-5">
                    <b1><a href="/books/book/${book.id}">${book.name}</a></b1>
                </div>
                <div class="row mb-3 align-items-left">
                    <div class="col-lg-2 col-sm-12 offset-lg-4">
                        <div class="image-container">
                            <#if book.getDirectLinkToImage()??>
                                <img src="${book.getDirectLinkToImage()}" alt="book cover image" class="img-fluid"
                                     style="max-width: 125px; height: 130px; padding-bottom: 5px;">
                            <#else>
                                <img src="${book.getPlaceHolder()}" alt="book cover placeholder" class="img-fluid"
                                     style="max-width: 125px; height: 130px; padding-top: 10px;">
                            </#if>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-12">
                        <p class="books-p"> By&nbsp;
                            <#list book.authors as author>
                                ${author.name()}
                            </#list>
                        </p>
                        <p class="books-p">Genre:&nbsp; <a
                                    href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>
                        <#if book.averageRating() == 0.0>
                            <p class="books-p">Not rated yet</p>
                        <#else>
                            <p class="books-p">${book.averageRating()}</p>
                        </#if>
                        <p class="books-p">${book.price}&nbsp;UAH</p>

                    </div>
                    <div class="col-lg-3 col-sm-12 d-flex">
                        <div class="mt-4">
                            <form method="post" action="/wishlist/cart/add-book/${book.id}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="bookId" value="${book.id}">
                                <button class="btn btn-success custom-btn-width" type="submit">Add to Cart</button>
                            </form>
                            <form method="post" action="/wishlist/remove/${book.id}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="bookId" value="${book.id}">
                                <button type="submit" class="btn btn-outline-danger custom-btn-width">Remove</button>
                            </form>
                        </div>
                    </div>
                </div>
            </#list>
        </div>




    <#else>
        <p>Empty yet</p>
    </#if>
<#--    <a href="/books" class="btn btn-primary">Return to books</a>-->



</@common.page>