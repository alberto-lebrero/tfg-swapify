package com.swapify.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Categoria {
      @Id
      private Long id;
      @Column(name="nombre")
      private String nombre;
      @Column(name = "descripción")
      private String descripcion;
}
