package com.swapify.modelo;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Categoria {
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      @Setter(AccessLevel.NONE)
      private Long id;

      private String nombre;

      @Column(name = "descripción")
      private String descripcion;

}
