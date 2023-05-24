<#import "parts/common.ftlh" as common>
<@common.page>
    <#include "parts/security.ftlh">
    <div class="d-flex justify-content-between" style="max-width: 700px; margin-left: auto; margin-right: auto;">
        <#if (search??) && (search?has_content)>
            <form action="/books" method="get" class="me-2">
                <input type="hidden" id="search" name="search">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-outline-success" type="submit">Back To All</button>
            </form>
        </#if>
        <div class="btn-group me-3">
            <button class="btn btn-outline-success dropdown-toggle" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                Sort by:
                <#if sortOption??>
                    <#switch sortOption>
                        <#case "name">
                            Name
                            <#break>
                        <#case "priceAsc">
                            Price (Low to High)
                            <#break>
                        <#case "priceDesc">
                            Price (High to Low)
                            <#break>
                    </#switch>
                </#if>
            </button>
            <form id="sortForm" action="/books/sort" method="get" role="search">
                <input type="hidden" id="sortOption" name="sortOption">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" id="sort-name" href="#" data-sort="name">Name</a></li>
                    <li><a class="dropdown-item" id="sort-priceAsk" href="#" data-sort="priceAsc">Price (Low to High)</a></li>
                    <li><a class="dropdown-item" id="sort-priceDesk" href="#" data-sort="priceDesc">Price (High to Low)</a></li>
                </ul>
            </form>
        </div>

        <div class="flex-grow-1">
            <form class="d-flex" role="search" action="/books" method="get">
                <input class="form-control me-2 flex-grow-1" type="search" name="search" placeholder="Search"
                       aria-label="Search">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
    <br>
    <#if (search??) && (search?has_content)>
        <h2>Search results for: ${search}</h2>
    <#else>
        <h2>All Books</h2>
    </#if>

    <div class="container align-items-center">
        <#list books.content as book>
        <div class="col-lg-7 col-sm-12 offset-lg-5">
            <b1><a href="/books/book/${book.id}">${book.name}</a></b1>
        </div>
            <div class="row mb-3 align-items-left">
                <div class="col-lg-2 col-sm-12 offset-lg-4">
                    <div class="image-container">
                        <#if book.getDirectLinkToImage()??>
                            <img src="${book.getDirectLinkToImage()}" alt="book cover image" class="img-fluid" style="max-width: 125px; height: auto;">
                        <#else>
                            <img src="${book.getPlaceHolder()}" alt="book cover placeholder" class="img-fluid" style="max-width: 125px; height: auto;">
                        </#if>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-12">
                    <p class="books-p"> By&nbsp;
                        <#list book.authors as author>
                            ${author.name()}
                        </#list>
                    </p>
                    <p class="books-p">Genre:&nbsp; <a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>
                    <#if book.averageRating() == 0.0>
                        <p class="books-p">Not rated yet</p>
                    <#else>
                        <p class="books-p">${book.averageRating()}</p>
                    </#if>
                    <p class="books-p">${book.price}&nbsp;UAH</p>
                    <div class="d-flex justify-content-center">
                        <form method="post" action="/cart/add-book/${book.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="bookId" value="${book.id}">
                            <button class="btn btn-success" type="submit">Add to Cart</button>
                        </form>
                        <span style="margin: 0 2px;"></span>
                        <form method="post" action="/wishlist/add-book/${book.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="bookId" value="${book.id}">
                            <button class="btn btn-outline-warning" type="submit">Add To Wish List</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-12">
                    <#if isAdmin>

                        <form class="d-flex mb-2" role="form"  action="/change-price/${book.id}" method="post">
<#--                            <label for="newPrice">New Price:</label>-->
                            <input class="form-control me-2 flex-grow-1" type="number" id="newPrice" placeholder="New Price"
                                   aria-label="New Price" name="newPrice" step="0.01" min="0" required>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="bookId" value="${book.id}">
                            <button type="submit" class="btn btn-warning">Change</button>
                        </form>

                        <form class="d-flex mb-2" role="form" action="/change-quantity/${book.id}" method="post">
<#--                            <label for="newQuantity">New Quantity:</label>-->
                            <input class="form-control me-2 flex-grow-1" placeholder="New Quantity"
                                   aria-label="New Quantity" type="number" id="newQuantity" name="newQuantity" step="1" min="1" required>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="bookId" value="${book.id}">
                            <button type="submit" class="btn btn-warning">Change</button>
                        </form>

                        <form class="d-flex mb-2" role="form" action="/change-image/${book.id}" method="post">
<#--                            <label for="newImage">New Image(Google drive link):</label>-->
                            <input class="form-control me-2 flex-grow-1" placeholder="New Image Link"
                                   aria-label="New Image Link" type="text" id="newImage" name="newImage" required>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="bookId" value="${book.id}">
                            <button type="submit" class="btn btn-warning">Change</button>
                        </form>
                        <form action="/delete-book/${book.id}" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="bookId" value="${book.id}">
                            <button type="submit" class="btn btn-danger w-100">Delete</button>
                        </form>

                    <#else>
                        <!-- Empty space for non-admin users -->
                    </#if>
                </div>
            </div>
        </#list>
    </div>

    <!-- Navigation Links -->
    <#if currentPage?number gt 0>
        <form action="/books" method="get" class="d-inline">
            <input type="hidden" name="page" value="${currentPage - 1}">
            <input type="hidden" name="sortOption" value="${sortOption!}">
            <button type="submit" class="btn btn-primary">Previous</button>
        </form>
    </#if>

    Page ${currentPage + 1} of ${totalPages}

    <#if currentPage < totalPages - 1>
        <form action="/books" method="get" class="d-inline">
            <input type="hidden" name="page" value="${currentPage + 1}">
            <input type="hidden" name="sortOption" value="${sortOption!}">
            <button type="submit" class="btn btn-primary">Next</button>
        </form>
    </#if>

    <script>
        document.querySelectorAll('#sort-name, #sort-priceAsk, #sort-priceDesk').forEach(item => {
            item.addEventListener('click', function (e) {
                e.preventDefault();
                const sortOption = this.getAttribute('data-sort');
                document.getElementById('sortOption').value = sortOption;
                document.getElementById('sortForm').submit();
            });
        });
    </script>
</@common.page>
