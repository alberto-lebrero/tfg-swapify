package com.swapify.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Perfil {
      @Id
      @Column(nullable = false, unique = true)
      @Setter(AccessLevel.NONE)
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
