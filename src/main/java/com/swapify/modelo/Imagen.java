package com.swapify.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.net.URL;

@Entity
public class Imagen {
      @Id
      private Long id;
      @Column(name = "url")
      private String url;
      @Column(name = "orden")
      private int orden;
}
