package com.swapify.modelo;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Categoria {
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @Column(name="nombre")
      private String nombre;
      @Column(name = "descripción")
      private String descripcion;
}
