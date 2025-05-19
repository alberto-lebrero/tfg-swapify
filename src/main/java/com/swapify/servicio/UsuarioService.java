package com.swapify.servicio;

import com.swapify.modelo.Usuario;
import com.swapify.persistencia.UsuarioJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {
      private final UsuarioJPADAO usuarioJPADAO;

      @Autowired
      public UsuarioService(UsuarioJPADAO usuarioJPADAO) {
            this.usuarioJPADAO = usuarioJPADAO;
      }

      public void crearUsuario(Usuario usuarioACrear) {
            usuarioJPADAO.create(usuarioACrear);
      }

      @Transactional(readOnly = true)
      public Usuario encontrarUsuario(String id) {
            return usuarioJPADAO.find(id);
      }

      @Transactional(readOnly = true)
      public List<Usuario> listarUsuarios() {
            return usuarioJPADAO.findAll();
      }

      public void actualizarUsuario(Usuario usuarioAActualizar) {
            usuarioJPADAO.update(usuarioAActualizar);
      }

      public void eliminarUsuario(Usuario usuarioAEliminar) {
            usuarioJPADAO.delete(usuarioAEliminar);
      }

      public Usuario autenticar(String email, String contrasenna) {
            Usuario usuario = usuarioJPADAO.obtenerUsuarioPor(email);
            if(usuario != null && usuario.getContrasenna().equals(contrasenna)) {
                  return usuario;
            }
            return null;
      }
}
