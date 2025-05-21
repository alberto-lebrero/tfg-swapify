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
public class Oferta {
      @Id
      @GeneratedValue(strategy= GenerationType.IDENTITY)
      @Setter(AccessLevel.NONE)
      private Long id;

      private String tipo;
      private boolean estado;
      private String detalle;

      @ManyToOne
      @JoinColumn(name = "fk_usuario_email", nullable = false)
      private Usuario usuario;

      @ManyToOne
      @JoinColumn(name = "fk_publicaciones_id", nullable = false)
      private Publicacion publicaciones;
}
