package com.swapify.persistencia;

import com.swapify.modelo.Usuario;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAO extends JPADAO<Usuario, Long> {

      public UsuarioJPADAO() {
            super(Usuario.class);
      }

      public Usuario obtenerUsuarioPor(String email) throws NoResultException {
            try{
                  TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
                  query.setParameter("email", email);
                  return query.getSingleResult();
            } catch (NoResultException e) {
                  throw new DAOException("No se encontró ningún usuario con el email: " + email, e);
            } catch (Exception e) {
                  throw new DAOException("Error accediendo a la base de datos al buscar usuario por email", e);
            }
      }
}
