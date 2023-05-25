<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>MY</h1>
    <h2>bookstore</h2>

    <form class="d-flex" role="search" action="/books" method="get">
        <input class="form-control me-2"  type="search" placeholder="Search" name="search" aria-label="Search">
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
        <#list book.authors as author>
            <p>${author.name()}</p>
        </#list>
        <p><a href="/books/genre/${book.genre}">${book.genre.getDisplayName()}</a></p>

        <div class="button-container">
            <button onclick="location.reload()" id="update-book" class="btn btn-outline-success" type="submit">Try another</button>
        </div>
    </div>
    </section>


<#--    <div style="margin-top: 25px;">-->
<#--    <footer class="footer py-3 bg-light">-->
<#--        <div class="container">-->
<#--            <p id="about-creator" class="text-muted"></p>-->
<#--            <p id="about-description" class="text-muted"></p>-->
<#--        </div>-->
<#--    </footer>-->
<#--    </div>-->
<#--    <script>-->
<#--        fetch('http://localhost:8080/api/about') // обновленный URL-->
<#--            .then(response => response.json())-->
<#--            .then(data => {-->
<#--                document.getElementById('about-creator').textContent = "Автор: " + data.creator;-->
<#--                document.getElementById('about-description').textContent = "О проекте: " + data.description;-->
<#--            });-->
<#--    </script>-->
</@common.page>