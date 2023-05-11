<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>Login</h1>
    <form method="post" action="/login">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Login</button>
    </form>
    <br>
    <p>Don't have an account yet? Rather join!</p>
    <button onclick="location.href='/registration'" id="registration-button">Registration</button>
</@common.page>
