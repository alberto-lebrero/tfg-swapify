package com.swapify.modelo;

import jakarta.persistence.*;

import java.net.URL;

@Entity
public class Imagen {
      @Id
      @GeneratedValue(strategy= GenerationType.IDENTITY)
      private Long id;
      @Column(name = "url")
      private String url;
      @Column(name = "orden")
      private int orden;
}
