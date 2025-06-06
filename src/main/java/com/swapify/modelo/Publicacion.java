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

      @ManyToOne
      @JoinColumn(name = "fk_categoria_id", nullable = false)
      private Categoria categoria;

      @OneToMany(mappedBy = "publicaciones", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Oferta> ofertas;

      @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Imagen> imagenes = new ArrayList<>();
}
