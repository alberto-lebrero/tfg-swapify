package com.swapify.persistencia;

import com.swapify.modelo.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAO extends JPADAO<Usuario, String> {

      public UsuarioJPADAO() {
            super(Usuario.class);
      }
}
