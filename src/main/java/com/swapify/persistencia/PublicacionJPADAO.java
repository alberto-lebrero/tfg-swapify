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

      public List<Publicacion> encontrarPorTituloYTipo(String titulo, List<String> tipos) {
            TypedQuery<Publicacion> consulta = em.createQuery(
                    "SELECT p FROM Publicacion p WHERE" +
                            "(:titulo IS NULL OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) " +
                    "AND (:tipos IS NULL OR p.categoria IN :tipos)", Publicacion.class
            );

            consulta.setParameter("titulo", (titulo == null || titulo.isEmpty()) ? null : titulo);
            consulta.setParameter("tipos", (tipos == null || tipos.isEmpty()) ? null : tipos);

            return consulta.getResultList();
      }
}
;