package com.swapify.persistencia;

import com.swapify.modelo.Perfil;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

@Repository
public class PerfilJPADAO extends JPADAO<Perfil, Long> {
      public PerfilJPADAO() {
            super(Perfil.class);
      }

      public Perfil encontrarPerfilPorUsuarioId(Long idUsuario) throws DAOException {
            try {
                  return em.createQuery(
                                  "SELECT p FROM Perfil p WHERE p.usuario.id = :idUsuario", Perfil.class)
                          .setParameter("idUsuario", idUsuario)
                          .getSingleResult();
            } catch (NoResultException e) {
                  return null;
            } catch (Exception e) {
                  throw new DAOException("Error al recuperar el perfil del usuario con ID: " + idUsuario, e);
            }
      }

}
