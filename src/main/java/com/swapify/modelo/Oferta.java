package com.swapify.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Oferta {
      @Id
      private Long id;
      @Column(name = "tipo")
      private String tipo;
      @Column(name = "estado")
      private boolean estado;
      @Column(name = "detalle")
      private String detalle;
}
