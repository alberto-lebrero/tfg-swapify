document.addEventListener("DOMContentLoaded", () => {
    const button = document.getElementById("userMenuButton");
    const menu = document.getElementById("userMenu");

    if (button && menu) {
        button.addEventListener("click", (e) => {
            e.stopPropagation(); // Para evitar que el click también dispare el "cerrar menú"
            menu.classList.toggle("hidden");
        });

        document.addEventListener("click", function(event) {
            if (!button.contains(event.target) && !menu.contains(event.target)) {
                menu.classList.add("hidden");
            }
        });
    }
});
