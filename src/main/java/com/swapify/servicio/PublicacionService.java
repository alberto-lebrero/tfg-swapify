package com.swapify.servicio;

import com.swapify.modelo.Publicacion;
import com.swapify.persistencia.PublicacionJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PublicacionService {
      private final PublicacionJPADAO publicacionJPADAO;

      @Autowired
      public PublicacionService(PublicacionJPADAO publicacionJPADAO) {
            this.publicacionJPADAO = publicacionJPADAO;
      }

      public void crearPublicacion(Publicacion publicacionACrear) {
            publicacionJPADAO.create(publicacionACrear);
      }

      @Transactional(readOnly = true)
      public Publicacion encontrarPublicacion(Long id) {
            return publicacionJPADAO.find(id);
      }

      @Transactional(readOnly = true)
      public List<Publicacion> listarPublicacion() {
            return publicacionJPADAO.findAll();
      }

      public void actualizarPublicacion(Publicacion publicacionAActualizar) {
            publicacionJPADAO.update(publicacionAActualizar);
      }

      public void eliminarPublicacion(Publicacion publicacionAEliminar) {
            publicacionJPADAO.delete(publicacionAEliminar);
      }
}
