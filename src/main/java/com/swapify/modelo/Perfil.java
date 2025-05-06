package com.swapify.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
public class Perfil {
      @Id
      @Column(nullable = false)
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

      public Perfil() {}
}
