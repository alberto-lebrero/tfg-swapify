package com.swapify.modelo;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Oferta {
      @Id
      @GeneratedValue(strategy= GenerationType.IDENTITY)
      private Long id;
      @Column(name = "tipo")
      private String tipo;
      @Column(name = "estado")
      private boolean estado;
      @Column(name = "detalle")
      private String detalle;
}
