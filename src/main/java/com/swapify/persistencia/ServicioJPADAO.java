package com.swapify.persistencia;

import com.swapify.modelo.Servicio;
import jakarta.persistence.EntityManager;

public class ServicioJPADAO extends JPADAO<Servicio,String> {
      public ServicioJPADAO(EntityManager em) { super(em, Servicio.class); }
}
