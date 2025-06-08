document.addEventListener("DOMContentLoaded", function() {
    function mostrarCampos() {
        const tipoBien = document.getElementById('tipoBien').checked;
        const tipoServicio = document.getElementById('tipoServicio').checked;
        const contenedor = document.getElementById('camposAdicionales');

        if (tipoBien) {
            contenedor.innerHTML = `
                <div class="mb-4 grid grid-cols-1 gap-4">
                    <div>
                        <label class="block text-gray-700 font-semibold mb-2" for="estado">Estado</label>
                        <input type="text" id="estado" name="estado" th:field="*{estado}" class="w-full px-4 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"/>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-semibold mb-2" for="disponible">Disponible</label>
                        <input type="checkbox" id="disponible" name="disponible" th:field="*{disponible}" class="mr-2"/>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-semibold mb-2" for="ancho">Ancho (cm)</label>
                        <input type="number" id="ancho" name="ancho" th:field="*{ancho}" step="0.01" min="0" class="w-full px-4 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"/>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-semibold mb-2" for="alto">Alto (cm)</label>
                        <input type="number" id="alto" name="alto" th:field="*{alto}" step="0.01" min="0" class="w-full px-4 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"/>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-semibold mb-2" for="profundidad">Profundidad (cm)</label>
                        <input type="number" id="profundidad" name="profundidad" th:field="*{profundidad}" step="0.01" min="0" class="w-full px-4 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"/>
                    </div>
                </div>
            `;
        } else if (tipoServicio) {
            contenedor.innerHTML = `
                <div class="mb-4 grid grid-cols-1 gap-4">
                    <div>
                        <label class="block text-gray-700 font-semibold mb-2" for="horas">Horas</label>
                        <input type="number" id="horas" name="horas" th:field="*{horas}" min="0" class="w-full px-4 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"/>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-semibold mb-2" for="minutos">Minutos</label>
                        <input type="number" id="minutos" name="minutos" th:field="*{minutos}" min="0" max="59" class="w-full px-4 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"/>
                    </div>
                </div>
            `;
        } else {
            contenedor.innerHTML = "";
        }
    }

    // Asocia el evento a los radioButton
    const radioBien = document.getElementById('tipoBien');
    const radioServicio = document.getElementById('tipoServicio');
    if (radioBien && radioServicio) {
        radioBien.addEventListener('change', mostrarCampos);
        radioServicio.addEventListener('change', mostrarCampos);
    }
});
