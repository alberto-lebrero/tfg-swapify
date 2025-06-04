
document.addEventListener("DOMContentLoaded", function () {
    let current = 0;
    function actualizarCarrusel() {
        const carrusel = document.getElementById("carouselImages");
        const total = carrusel.children.length;
        const ancho = carrusel.clientWidth;
        carrusel.style.transform = `translateX(-${current * ancho}px)`;
    }

    function siguiente() {
        const carrusel = document.getElementById("carouselImages");
        if (current < carrusel.children.length - 1) {
            current++;
            actualizarCarrusel();
        }
    }

    function anterior() {
            if (current > 0) {
                current--;
                actualizarCarrusel();
            }
        }
        window.addEventListener("resize", actualizarCarrusel);
});