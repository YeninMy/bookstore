<#import "parts/common.ftlh" as common>
<@common.page>
    <h1>${user.username}</h1>
    <p>${user.email}</p>
    <div class="button-container-2">
        <a href="/purchases" class="btn btn-outline-success custom-btn-width-p">List of Purchases</a>
        <button class="btn btn-outline-warning custom-btn-width-p" id="openModalButton2">Change Password</button>
    </div>
    <#if errorOldPasswordProfile?? || errorNewPasswordProfile?? || errorPasswordConfirmationProfile?? >
        <div class="alert alert-danger" role="alert">
            Errors in form, try again.
        </div>
    </#if>
    <div id="passwordModal" class="modal">
        <div class="modal-content">
            <form class="rf" method="post" action="/profile">
                <h1>Change password:</h1>
                <br>
                <input class="form-control me-2 flex-grow-1" placeholder="Old password" type="password"
                       name="old-password" id="old-password">
                <#if errorOldPasswordProfile??>
                    <div class="alert alert-danger" role="alert">
                        ${errorOldPasswordProfile}
                    </div>
                </#if>
                <input class="form-control me-2 flex-grow-1" placeholder="New password" type="password"
                       name="new-password" id="new-password">
                <#if errorNewPasswordProfile??>
                    <div class="alert alert-danger" role="alert">
                        ${errorNewPasswordProfile}
                    </div>
                </#if>
                <input class="form-control me-2 flex-grow-1" placeholder="Confirm password" type="password"
                       name="confirm-password" id="confirm-password">
                <#if errorPasswordConfirmationProfile??>
                    <div class="alert alert-danger" role="alert">
                        ${errorPasswordConfirmationProfile}
                    </div>
                </#if>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input class="btn btn-outline-success custom-btn-width-2" type="submit" value="Change">
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