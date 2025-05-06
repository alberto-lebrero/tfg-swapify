package com.swapify.persistencia;

import com.swapify.modelo.Bien;
import org.springframework.stereotype.Repository;

@Repository
public class BienJPADAO extends JPADAO<Bien, String> {

      public BienJPADAO() {
            super(Bien.class);
      }
}
