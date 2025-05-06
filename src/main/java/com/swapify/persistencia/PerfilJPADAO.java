package com.swapify.persistencia;

import jakarta.persistence.EntityManager;
import com.swapify.modelo.Perfil;

public class PerfilJPADAO extends JPADAO<Perfil, String> {
      public PerfilJPADAO(EntityManager em) {
            super(em, Perfil.class);
      }
}
