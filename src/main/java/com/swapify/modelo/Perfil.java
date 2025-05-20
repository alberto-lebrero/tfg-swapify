package com.swapify.modelo;

import jakarta.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Perfil {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(nullable = false, unique = true)
      private String nif;

      private String nombre;
      private String primerApellido;
      private String segundoApellido;
      private String telefono;
      private String descripcion;
      private String direccion;
      private String provincia;
      private String codigoPostal;
      private String pais;

      @OneToOne(mappedBy = "perfil")
      private Usuario usuario;
}
