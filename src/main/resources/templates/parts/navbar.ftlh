<#--<#assign person = Session.SPRING_SECURITY_CONTEXT??>-->
<#include "security.ftlh">
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">MYBookstore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/books">Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/cart">Cart ${openPurchase}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/wishlist">Wishlist ${wishes}</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/profile" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">Profile</a>
                    <ul class="dropdown-menu">
                        <#if known>
                            <li><a class="dropdown-item" href="/profile" id="profileLink">${name}</a></li>
                            <li><a class="dropdown-item" href="/logout" id="logoutLink">Logout</a></li>
                        <#else>
                            <li><a class="dropdown-item" href="/login" id="loginLink">Login</a></li>
                            <li><button id="openModalButton" class="dropdown-item" >Registration</button></li>
                        </#if>

                        <#--                        <li><hr class="dropdown-divider"></li>-->
                    </ul>
                </li>
                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/admin-panel">Admin Panel</a>
                </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>
<#include "registration-form.ftlh">