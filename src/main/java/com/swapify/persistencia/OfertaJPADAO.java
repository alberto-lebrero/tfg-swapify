package com.swapify.persistencia;

import com.swapify.modelo.Oferta;
import com.swapify.modelo.Perfil;
import jakarta.persistence.EntityManager;

public class OfertaJPADAO extends JPADAO<Oferta, Long> {
      public OfertaJPADAO(EntityManager em) {
            super(em, Oferta.class);
      }
}
