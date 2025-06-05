package com.swapify.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "email")
@ToString(exclude = "contrasenna")
public class Usuario {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(nullable = false, unique = true)
      @NonNull
      private String email;

      @Column(name = "contraseña", nullable = false)
      @NonNull
      private String contrasenna;

      @OneToOne(cascade = CascadeType.ALL)
      @JoinColumn(name = "fk_perfil_id", referencedColumnName = "id")
      private Perfil perfil;

      @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Publicacion> publicaciones = new ArrayList<>();

      @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Oferta> ofertas = new ArrayList<>();
}