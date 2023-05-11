<#import "parts/common.ftlh" as common>
<@common.page>

    <h2>Registration form</h2>
    <form class="rf" method="post" action="/registration">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username"><br>
        <#if errorUsername??>
            <div class="invalid-feedback" style="display: block; color: red;">
                ${errorUsername}
            </div>
        </#if>
        <label for="email">Email:</label>
        <input type="text" name="email" id="email"><br>
        <#if errorEmail??>
            <div class="invalid-feedback" style="display: block; color: red;">
                ${errorEmail}
            </div>
        </#if>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password"><br>
        <#if errorPassword??>
            <div class="invalid-feedback" style="display: block; color: red;">
                ${errorPassword}
            </div>
        </#if>
        <label for="confirm-password">Confirm password:</label>
        <input type="password" name="confirm-password" id="confirm-password"><br>
        <#if errorPasswordConfirmation??>
            <div class="invalid-feedback" style="display: block; color: red;">
                ${errorPasswordConfirmation}
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Add">
    </form>
    <br>
    <p>Already have an account?</p>
    <button onclick="location.href='/login'" id="registration-button">login</button>
</@common.page>