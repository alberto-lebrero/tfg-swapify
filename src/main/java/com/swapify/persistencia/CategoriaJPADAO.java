package com.swapify.persistencia;

import com.swapify.modelo.Categoria;
import jakarta.persistence.EntityManager;

public class CategoriaJPADAO extends JPADAO<Categoria, Long> {
      public CategoriaJPADAO(EntityManager em){ super(em, Categoria.class); }
}
