package com.swapify;

import com.swapify.modelo.Usuario;
import com.swapify.persistencia.UsuarioJPADAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
      public static void main(String[] args) {
            SpringApplication.run(Main.class, args);
      }

      @Bean
      public CommandLineRunner testUsuarioDAO(UsuarioJPADAO usuarioDAO) {
            return args -> {
                  //Usuario usuarioNuevo = new Usuario("alberto.lebrero01@gmail.com","alberto.lebrero01");
                  //usuarioDAO.crear(usuarioNuevo);

                  Usuario usuarioNuevo_2 = new Usuario("luisa.martinez@gmail.com","luisa.martinez");
                  usuarioDAO.create(usuarioNuevo_2);

                  System.out.println("Usuarios en la BBDD:");
                  usuarioDAO.findAll().forEach(usuario ->
                          System.out.println("Email: " + usuario.getEmail() + ", " + "Contraseña: " + usuario.getContrasenna()));
            };
      }
}