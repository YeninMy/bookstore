<#import "parts/common.ftlh" as common>
<@common.page>

    <h1>${user.username}</h1>
    <p>${user.email}</p>
    <a href="/purchases" class="btn btn-primary">List of Purchases</a>



    <button id="openModalButton2">Change Password</button>
    <#if errorOldPassword?? || errorNewPassword?? || errorPasswordConfirmation?? >
        <div class="invalid-feedback" style="display: block; color: red;">
            Errors, in form try again.
        </div>
    </#if>

    <div id="passwordModal" class="modal">
        <div class="modal-content">
            <form class="rf" method="post" action="/profile">
                <h1>Change password:</h1>
                <label for="old-password">Old Password:</label>
                <input type="password" name="old-password" id="old-password"><br>
                <#if errorOldPassword??>
                    <div class="invalid-feedback" style="display: block; color: red;">
                        ${errorOldPassword}
                    </div>
                </#if>

                <label for="new-password">New password:</label>
                <input type="password" name="new-password" id="new-password"><br>
                <#if errorNewPassword??>
                    <div class="invalid-feedback" style="display: block; color: red;">
                        ${errorNewPassword}
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
                <input type="submit" value="Change">
            </form>
        </div>
    </div>
    <script>
        var modal = document.getElementById("passwordModal");
        var btn = document.getElementById("openModalButton2");

        btn.onclick = function () {
            modal.style.display = "block";
        }

        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</@common.page>