package com.swapify.persistencia;

import jakarta.persistence.EntityManager;
import com.swapify.modelo.Publicacion;

public class PublicacionJPADAO extends JPADAO<Publicacion, Long> {

      public PublicacionJPADAO(EntityManager em){
            super(em, Publicacion.class);
      }
}
