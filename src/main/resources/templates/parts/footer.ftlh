<footer class="footer py-3 bg-light">
    <div class="container">
        <p class="s1" id="about-creator" class="text-muted"></p>
        <p class="s2" id="about-description" class="text-muted"></p>
    </div>
</footer>
<script>
    fetch('http://localhost:8080/api/about')
        .then(response => response.json())
        .then(data => {
            document.getElementById('about-creator').textContent = "by " + data.creator;
            document.getElementById('about-description').textContent =  data.description;
        });
</script>