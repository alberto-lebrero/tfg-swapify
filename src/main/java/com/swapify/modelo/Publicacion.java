package com.swapify.modelo;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
public class Publicacion {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Setter(AccessLevel.NONE)
      private Long id;

      private String titulo;
      private String descripcion;
      private double precio;
      private LocalDateTime fecha;

      @ManyToOne
      @JoinColumn(name = "fk_usuario_email", nullable = false)
      private Usuario usuario;
}
