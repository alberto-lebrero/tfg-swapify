package com.swapify.servicio;

import com.swapify.modelo.Perfil;
import com.swapify.modelo.Usuario;
import com.swapify.persistencia.PerfilJPADAO;
import com.swapify.persistencia.UsuarioJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.swapify.persistencia.DAOException;

@Service
@Transactional
public class PerfilService {
      private final PerfilJPADAO perfilJPADAO;
      private final UsuarioJPADAO usuarioJPADAO;

      @Autowired
      public PerfilService(PerfilJPADAO perfilJPADAO, UsuarioJPADAO usuarioJPADAO) {
            this.perfilJPADAO = perfilJPADAO;
            this.usuarioJPADAO = usuarioJPADAO;
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

      public void eliminarPerfilDeUsuario(Long idUsuario) throws DAOException {
            // Busca el usuario asociado al perfil
            Usuario usuario = usuarioJPADAO.find(idUsuario);
            if (usuario != null && usuario.getPerfil() != null) {
                  Perfil perfil = usuario.getPerfil();
                  usuario.setPerfil(null); // desasocio el perfil
                  usuarioJPADAO.update(usuario); // guardo el cambio
                  perfilJPADAO.delete(perfil); // Elimino explícitamente el perfil
            }
      }
}
