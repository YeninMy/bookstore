<#import "parts/common.ftlh" as common>
<@common.page>
<h1>My purchases</h1>
<table>
    <thead>
    <tr>
        <th>Purchase</th>
        <th>Books</th>
    </tr>
    </thead>
    <tbody>
    <#list purchases as purchase>
        <tr>
            <td>${purchase.getFormattedDate()}</td>
            <td>
                <ul>
                    <#list purchase.books as book>
                        <li>${book.name} (${book.price})</li>
                    </#list>
                </ul>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
<br>
</@common.page>