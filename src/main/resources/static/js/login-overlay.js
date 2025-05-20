document.addEventListener("DOMContentLoaded", () => {
    const openLogin = document.getElementById("open-login");
    const closeLogin = document.getElementById("close-login");
    const overlay = document.getElementById("login-overlay");

    openLogin?.addEventListener("click", () => {
        overlay.classList.remove("hidden");
    });

    closeLogin?.addEventListener("click", () => {
        overlay.classList.add("hidden");
    });

    overlay?.addEventListener("click", (e) => {
        if (e.target === overlay) {
            overlay.classList.add("hidden");
        }
    });
});
