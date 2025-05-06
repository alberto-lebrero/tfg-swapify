package com.swapify.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Servicio extends Publicacion {
      private int horas;
      private int minutos;
}
