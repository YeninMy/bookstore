<#import "parts/common.ftlh" as common>
<@common.page>
    <h2>Add a new book</h2>
    <form class="rf" action="/add-book" method="post">
        <input class="form-control me-2 flex-grow-1" placeholder="Name" type="text" name="name" id="name" required>
        <select class="form-control me-2 flex-grow-1" id="genre" name="genre" required>
            <option class="grey" disabled selected>Select Genre</option>
            <option value="SCIENCE_FICTION">Science Fiction</option>
            <option value="FANTASY">Fantasy</option>
            <option value="MYSTERY">Mystery</option>
            <option value="THRILLER">Thriller</option>
            <option value="ROMANCE">Romance</option>
            <option value="HORROR">Horror</option>
            <option value="HISTORICAL_FICTION">Historical Fiction</option>
        </select>
        <input class="form-control me-2 flex-grow-1" placeholder="Price in UAH" type="number" id="price" name="price"
               step="0.01" min="0" required>

        <input class="form-control me-2 flex-grow-1" placeholder="Quantity" type="number" id="quantity" name="quantity"
               step="1" min="1" required>
        <input class="form-control me-2 flex-grow-1" placeholder="Image's google drive link" type="text"
               name="coverImage" id="coverImage" required>
        <div class="form-control me-2 flex-grow-1" id="authors">
            <input class="form-control me-2 flex-grow-1" placeholder="Author first name" type="text"
                   id="authors[0].firstName" name="authors[0].firstName" required>
            <input class="form-control me-2 flex-grow-1" placeholder="Author last name" type="text"
                   id="authors[0].lastName" name="authors[0].lastName" required>
        </div>
        <div class="mt-1">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-outline-success custom-btn-width" type="button" onclick="addAuthor()">Add Author
            </button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-success custom-btn-width" type="submit">Add Book</button>
        </div>
    </form>
    <script>
        var authorIndex = 0;

        function addAuthor() {
            authorIndex++;
            var div = document.createElement('div');
            div.id = 'author' + authorIndex;

            var firstNameInput = '<input class="form-control me-2 flex-grow-1" placeholder="Author first name" type="text" id="authors[' + authorIndex + '].firstName" name="authors[' + authorIndex + '].firstName" required>';
            var lastNameInput = '<input class="form-control me-2 flex-grow-1" placeholder="Author last name" type="text" id="authors[' + authorIndex + '].lastName" name="authors[' + authorIndex + '].lastName" required>';
            var removeButton = '<button class="btn btn-outline-danger" type="button" onclick="removeAuthor(' + authorIndex + ')">Remove Author</button>';

            div.innerHTML = firstNameInput + lastNameInput + removeButton;

            document.getElementById('authors').appendChild(div);
        }

        function removeAuthor(index) {
            var div = document.getElementById('author' + index);
            div.remove();
        }
    </script>
</@common.page>