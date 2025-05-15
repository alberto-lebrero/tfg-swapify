package com.swapify.modelo;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Imagen {
      @Id
      @GeneratedValue(strategy= GenerationType.IDENTITY)
      @Setter(AccessLevel.NONE)
      private Long id;

      private String url;
      private int orden;

      @ManyToOne
      @JoinColumn(name = "fk_publicacion_id", nullable = false)
      private Publicacion publicacion;
}
