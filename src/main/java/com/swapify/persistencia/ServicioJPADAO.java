package com.swapify.persistencia;

import com.swapify.modelo.Servicio;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ServicioJPADAO extends JPADAO<Servicio,String> {
      public ServicioJPADAO() {
            super(Servicio.class);
      }
}
