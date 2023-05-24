<#import "parts/common.ftlh" as common>
<@common.page>

    <h2>Add a new book</h2>
    <form action="/add-book" method="post">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" min="0" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" step="1" min="0" required>

        <label for="coverImage">Google Drive Link to the image:</label>
        <input type="text" name="coverImage" id="coverImage" required><br>

        <div id="authors">
            <label for="authors[0].firstName">Author First Name:</label>
            <input type="text" id="authors[0].firstName" name="authors[0].firstName" required>

            <label for="authors[0].lastName">Author Last Name:</label>
            <input type="text" id="authors[0].lastName" name="authors[0].lastName" required>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="button" onclick="addAuthor()">Add Another Author</button>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Add book</button>

    </form>


    <script>
        var authorIndex = 0;

        function addAuthor() {
            authorIndex++;
            var div = document.createElement('div');
            div.innerHTML =
                '<label for="authors[' + authorIndex + '].firstName">Author First Name:</label>' +
                '<input type="text" id="authors[' + authorIndex + '].firstName" name="authors[' + authorIndex + '].firstName" required>' +
                '<label for="authors[' + authorIndex + '].lastName">Author Last Name:</label>' +
                '<input type="text" id="authors[' + authorIndex + '].lastName" name="authors[' + authorIndex + '].lastName" required>';
            document.getElementById('authors').appendChild(div);
        }
    </script>
</@common.page>