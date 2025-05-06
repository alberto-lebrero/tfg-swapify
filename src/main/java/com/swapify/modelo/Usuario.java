package com.swapify.modelo;

import jakarta.persistence.*;
import com.swapify.modelo.Perfil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@RequiredArgsConstructor
@Getter
public class Usuario {
      @Id
      @Column(nullable = false, unique = true)
      @NonNull
      private String email;

      @Column(name = "contraseña", nullable = false)
      @Setter
      @NonNull
      private String contrasenna;

      @OneToOne(cascade = CascadeType.ALL)
      @JoinColumn(name = "fk_perfil_nif", referencedColumnName = "nif")
      @Setter
      private Perfil perfil;

      @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Publicacion> publicaciones = new ArrayList<>();

      /**
       *
       * Es requerido por JPA.
       */
      public Usuario() {}



      //Codificar equals, hashCode y toString
}
