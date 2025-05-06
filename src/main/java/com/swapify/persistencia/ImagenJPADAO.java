package com.swapify.persistencia;

import com.swapify.modelo.Imagen;
import org.springframework.stereotype.Repository;

@Repository
public class ImagenJPADAO extends JPADAO<Imagen, Long>{
      public ImagenJPADAO() {
            super(Imagen.class);
      }
}
