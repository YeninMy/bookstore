<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>MY</h1>
    <h2>bookstore</h2>
    <form class="d-flex" role="search" action="/books" method="get">
        <input class="form-control me-2" type="search" placeholder="Search" name="search" aria-label="Search">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
    <section class="user-info">
        <#if user??>
            <h2>Welcome, ${user.username}, Book for you!!!</h2>
        <#else>
            <h2>Welcome, guest!</h2>
            <p>We sure you will like this book:</p>
        </#if>
    </section>
    <section class="random-book">
        <div id="random-book">
            <div class="image-container">
                <#if book.getDirectLinkToImage()??>
                    <img src="${book.getDirectLinkToImage()}" alt="book cover image" style="height: 250px">
                <#else>
                    <img src="${book.getPlaceHolder()}" alt="book cover placeholder" style="height: 250px">
                </#if>
            </div>
            <h3><a href="/books/book/${book.id}">${book.name}</a></h3>
            <#if book.averageRating() == 0.0 || book.averageRating()?is_nan>
                <div style="font-size: 18px;    justify-items: center;
    align-items: center; margin-left: auto; margin-right: auto">
                    <p>Not rated yet</p>
                </div>
            <#else>
                <div style="font-size: 18px;    justify-items: center;
    align-items: center; margin-left: auto; margin-right: auto; text-align: center">
                    <#assign rating = (book.averageRating()?number)?int />
                    <#list 1..5 as i>
                        <#if i <= rating>
                            <span class="fa fa-star" style="color: gold;"></span>
                        <#else>
                            <span class="fa fa-star-o"></span>
                        </#if>
                    </#list>
                </div>
            </#if>
            <p>
                <#if book.authors?size &gt; 1>
                    ${book.authors?first.name()}
                    <#list book.authors[1..] as author>
                        <br>${author.name()}
                    </#list>
                <#else>
                    ${book.authors?first.name()}
                </#if>
            </p>
            <#if book.genre?has_content>
                <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>
            </#if>
            <div class="button-container">
                <button onclick="location.reload()" id="update-book" class="btn btn-outline-success" type="submit">Try
                    another
                </button>
            </div>
        </div>
    </section>
</@common.page>