package com.swapify.persistencia;

import com.swapify.modelo.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaJPADAO extends JPADAO<Categoria, Long> {
      public CategoriaJPADAO(){
            super(Categoria.class);
      }
}
