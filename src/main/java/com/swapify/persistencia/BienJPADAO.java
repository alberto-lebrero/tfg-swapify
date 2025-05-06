package com.swapify.persistencia;

import com.swapify.modelo.Bien;
import jakarta.persistence.EntityManager;

public class BienJPADAO extends JPADAO<Bien, String> {

      public BienJPADAO(EntityManager em) { super(em, Bien.class); }
}
