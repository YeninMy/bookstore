<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
    person = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = person.getUsername()
    openPurchase = person.chosenBooks()
    wishes = person.wantedBooks()
    isAdmin = person.isAdmin()
    >
<#else>
    <#assign
    name = "Guest"
    openPurchase = ""
    wishes = ""
    isAdmin = false
    >
</#if>