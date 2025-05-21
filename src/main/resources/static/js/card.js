document.addEventListener("DOMContentLoaded", () => {
    const modalOverlay = document.getElementById('modalOverlay');
    const openModal = document.getElementById('openModal');
    const closeModal = document.getElementById('closeModal');
    const modalCard = document.getElementById('modalCard');

    openModal.addEventListener('click', () => {
        modalOverlay.classList.remove('hidden');
        modalOverlay.classList.add('flex');

        // Usar timeout para que el navegador detecte el cambio
        setTimeout(() => {
            modalCard.classList.remove('scale-95', 'opacity-0');
            modalCard.classList.add('scale-100', 'opacity-100');
        }, 10); // breve retardo para forzar transición
    });

    function cerrarModal() {
        // Transición de salida
        modalCard.classList.remove('scale-100', 'opacity-100');
        modalCard.classList.add('scale-95', 'opacity-0');

        // Esperar a que termine la animación antes de ocultar
        setTimeout(() => {
            modalOverlay.classList.remove('flex');
            modalOverlay.classList.add('hidden');
        }, 300); // mismo tiempo que `duration-300`
    }

    closeModal.addEventListener('click', cerrarModal);

    modalOverlay.addEventListener('click', (e) => {
        if (!modalCard.contains(e.target)) {
            cerrarModal();
        }
    });
});
