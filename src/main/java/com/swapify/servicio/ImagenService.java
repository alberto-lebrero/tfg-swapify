package com.swapify.servicio;

import com.swapify.modelo.Imagen;
import com.swapify.persistencia.ImagenJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImagenService {
      private final ImagenJPADAO imagenJPADAO;

      @Autowired
      public ImagenService(ImagenJPADAO imagenJPADAO) {
            this.imagenJPADAO = imagenJPADAO;
      }

      public void crearImagen(Imagen imagenACrear) {
            imagenJPADAO.create(imagenACrear);
      }

      @Transactional(readOnly = true)
      public Imagen encontrarImagen(Long id) {
            return imagenJPADAO.find(id);
      }

      @Transactional(readOnly = true)
      public List<Imagen> listarImagenes() {
            return imagenJPADAO.findAll();
      }

      public void actualizarImagen(Imagen imagenAActualizar) {
            imagenJPADAO.update(imagenAActualizar);
      }

      public void eliminarImagen(Imagen imagenAEliminar) {
            imagenJPADAO.delete(imagenAEliminar);
      }
}