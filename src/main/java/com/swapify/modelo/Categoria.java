package com.swapify.modelo;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

      @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Publicacion> publicaciones = new ArrayList<>();
}
