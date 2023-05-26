
    <footer class="footer py-3 bg-light">
        <div class="container">
            <p id="about-creator" class="text-muted"></p>
            <p id="about-description" class="text-muted"></p>
        </div>
    </footer>

<script>
    fetch('http://localhost:8080/api/about')
        .then(response => response.json())
        .then(data => {
            document.getElementById('about-creator').textContent = "Автор: " + data.creator;
            document.getElementById('about-description').textContent = "О проекте: " + data.description;
        });
</script>