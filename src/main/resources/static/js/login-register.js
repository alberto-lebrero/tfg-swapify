document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.material-icons[id^="togglePassword"]').forEach(toggle => {
        toggle.addEventListener('click', function() {
            // Encuentra el input asociado
            const input = this.parentElement.querySelector('input[type="password"], input[type="text"]');
            if (input.type === "password") {
                input.type = "text";
                this.textContent = "visibility_off";
            } else {
                input.type = "password";
                this.textContent = "visibility";
            }
        });
    });
});
