package com.swapify.controlador;

import com.swapify.modelo.Publicacion;
import com.swapify.modelo.Usuario;
import com.swapify.servicio.PublicacionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PublicacionController {
      @Autowired
      private PublicacionService publicacionService;

      @PostMapping("/publicacion")
      public String crearPublicacion(@ModelAttribute Publicacion publicacionACrear, HttpSession sesion) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioLogueado");

            if(usuarioActual != null) {
                  publicacionACrear.setUsuario(usuarioActual); //Asocio la publicacion al usuario que ha iniciado sesión
                  publicacionService.crearPublicacion(publicacionACrear);
                  return "redirect:/home";
            }
            return "redirect:/login";
      }

      // Es un proyecto MVC (Modelo Vista Controlador). API REST

      //¿? comprobar
      @GetMapping("/publicacion/{id}")
      public String verPublicacion(@PathVariable Long id, Model modelo) {
            Publicacion publicacion = publicacionService.encontrarPublicacion(id);

            if(publicacion != null) {
                  modelo.addAttribute("publicacion", publicacion);
                  return "publicacion/detalle";
            }
            return "redirect:/home";
      }

      @GetMapping("/publicacion")
      public String verPublicaciones(@RequestParam(value = "titulo", required = false) String titulo,
                                     @RequestParam(value = "tipos", required = false) List<String> tipos,
                                     Model modelo) {
            List<Publicacion> publicacionesAVisualizar = publicacionService.buscarPublicaciones(titulo, tipos);
            modelo.addAttribute("publicacionesAVisualizar", publicacionesAVisualizar);
            return "home";
      }

      @PutMapping("/publicacion/{id}")
      public String actualizarPublicacion(@PathVariable Long id, @ModelAttribute Publicacion publicacionAActualizar, HttpSession sesion) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioLogueado");

            if(usuarioActual != null) {
                  Publicacion publicacionOriginal = publicacionService.encontrarPublicacion(id);

                  if(publicacionOriginal != null && publicacionOriginal.getUsuario().getId().equals(usuarioActual.getId())) {
                        publicacionService.actualizarPublicacion(publicacionAActualizar);
                  }
                  return "redirect:/home";
            }
            return "redirect:/login";
      }

      @DeleteMapping("/publicacion/{id}")
      public void borrarPublicacion(@PathVariable Long id, HttpSession sesion) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioLogueado");

            if(usuarioActual != null) {
                  Publicacion publicacionAEliminar = publicacionService.encontrarPublicacion(id);
                  if(publicacionAEliminar != null && publicacionAEliminar.getUsuario().getId().equals(usuarioActual.getId())) {
                        publicacionService.eliminarPublicacion(publicacionAEliminar);
                  }
            }
      }
}
