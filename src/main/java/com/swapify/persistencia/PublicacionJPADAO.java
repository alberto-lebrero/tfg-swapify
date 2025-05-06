package com.swapify.persistencia;

import com.swapify.modelo.Publicacion;
import org.springframework.stereotype.Repository;

@Repository
public class PublicacionJPADAO extends JPADAO<Publicacion, Long> {

      public PublicacionJPADAO(){
            super(Publicacion.class);
      }
}
