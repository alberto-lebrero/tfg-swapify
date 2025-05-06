package com.swapify.persistencia;

import com.swapify.modelo.Imagen;
import jakarta.persistence.EntityManager;

public class ImagenJPADAO extends JPADAO<Imagen, Long>{
      public ImagenJPADAO(EntityManager em) { super(em, Imagen.class); }
}
