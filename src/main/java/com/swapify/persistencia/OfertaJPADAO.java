package com.swapify.persistencia;

import com.swapify.modelo.Oferta;
import org.springframework.stereotype.Repository;

@Repository
public class OfertaJPADAO extends JPADAO<Oferta, Long> {
      public OfertaJPADAO() {
            super(Oferta.class);
      }
}
