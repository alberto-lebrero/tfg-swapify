package com.swapify.persistencia;

import com.swapify.modelo.Publicacion;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublicacionJPADAO extends JPADAO<Publicacion, Long> {

      public PublicacionJPADAO(){
            super(Publicacion.class);
      }

      public List<Publicacion> encontrarPorTitulo(String titulo) {
            TypedQuery<Publicacion> consulta = em.createQuery(
                    "SELECT p FROM Publicacion p WHERE " +
                            "(:titulo IS NULL OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))", Publicacion.class
            );
            consulta.setParameter("titulo", (titulo == null || titulo.isEmpty()) ? null : titulo);
            return consulta.getResultList();
      }
}