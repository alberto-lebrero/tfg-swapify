package com.swapify.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Publicacion {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String titulo;
      private String descripcion;
      private double precio;
      private LocalDateTime fecha;

      @ManyToOne
      @JoinColumn(name = "fk_usuario_email", nullable = false)
      private Usuario usuario;
}
