package com.swapify.servicio;

import com.swapify.modelo.Perfil;
import com.swapify.persistencia.PerfilJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.swapify.persistencia.DAOException;

@Service
@Transactional
public class PerfilService {
      private final PerfilJPADAO perfilJPADAO;
      @Autowired
      public PerfilService(PerfilJPADAO perfilJPADAO) {
            this.perfilJPADAO = perfilJPADAO;
      }

      public void crearPerfil(Perfil perfilACrear) {
            perfilJPADAO.create(perfilACrear);
      }

      @Transactional(readOnly = true)
      public Perfil encontrarPerfil(Long id) {
            return perfilJPADAO.find(id);
      }

      @Transactional(readOnly = true)
      public Perfil encontrarPerfilDeUsuario(Long idUsuario) throws DAOException {
            return perfilJPADAO.encontrarPerfilPorUsuarioId(idUsuario);
      }

      @Transactional(readOnly = true)
      public List<Perfil> listarPerfiles() {
            return perfilJPADAO.findAll();
      }

      public void actualizarPerfil(Perfil perfilAActualizar) {
            perfilJPADAO.update(perfilAActualizar);
      }

      public void eliminarPerfil(Perfil perfilAEliminar) {
            perfilJPADAO.delete(perfilAEliminar);
      }
}
