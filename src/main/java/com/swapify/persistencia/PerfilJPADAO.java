package com.swapify.persistencia;

import com.swapify.modelo.Perfil;
import org.springframework.stereotype.Repository;

@Repository
public class PerfilJPADAO extends JPADAO<Perfil, String> {
      public PerfilJPADAO() {
            super(Perfil.class);
      }
}
