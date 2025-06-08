document.addEventListener("DOMContentLoaded", () => {
    // Función para abrir el modal y rellenar los campos
    function abrirModalEdicion(id, titulo, descripcion, precio) {
        document.getElementById('edit-id').value = id;
        document.getElementById('edit-titulo').value = titulo;
        document.getElementById('edit-descripcion').value = descripcion;
        document.getElementById('edit-precio').value = precio;

        // Setear acción del formulario para que apunte al endpoint update correcto
        document.getElementById('editForm').action = '/publicaciones/update/' + id;

        document.getElementById('editModalOverlay').classList.remove('hidden');
    }

    // Cerrar modal
    document.getElementById('closeEditModal').onclick = function() {
        document.getElementById('editModalOverlay').classList.add('hidden');
    }

    // Cerrar modal haciendo click fuera del card
    document.getElementById('editModalOverlay').onclick = function(e) {
        if (e.target === this) {
            this.classList.add('hidden');
        }
    }

});
