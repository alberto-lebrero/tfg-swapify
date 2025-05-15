package com.swapify.modelo;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bien extends Publicacion {
      private String estado;
      private boolean disponible;
      private double ancho;
      private double alto;
      private double profundidad;

}
