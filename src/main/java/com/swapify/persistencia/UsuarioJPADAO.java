package com.swapify.persistencia;

import com.swapify.modelo.Usuario;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAO extends JPADAO<Usuario, Long> {

      public UsuarioJPADAO() {
            super(Usuario.class);
      }

      public Usuario obtenerUsuarioPor(String email) throws NoResultException {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class).setParameter("email", email).getSingleResult();
      }
}
