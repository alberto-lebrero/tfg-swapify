package com.swapify.persistencia;

import jakarta.persistence.EntityManager;
import com.swapify.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAO extends JPADAO<Usuario, String> {

      public UsuarioJPADAO() {
            super(Usuario.class);
      }
}
