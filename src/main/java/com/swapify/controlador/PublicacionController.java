package com.swapify.controlador;

import com.swapify.modelo.Bien;
import com.swapify.modelo.Publicacion;
import com.swapify.modelo.Servicio;
import com.swapify.modelo.Usuario;
import com.swapify.servicio.PublicacionService;
import com.swapify.servicio.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/publicaciones")
public class PublicacionController {
      @Autowired
      private PublicacionService publicacionService;

      @Autowired
      private UsuarioService usuarioService;

      // LISTADO DE PUBLICACIONES (GET /publicaciones)
      @GetMapping("")
      public String verPublicaciones(
              @RequestParam(value = "titulo", required = false) String titulo,
              Model modelo
      ) {
            List<Publicacion> publicaciones = publicacionService.buscarPublicaciones(titulo);
            modelo.addAttribute("publicaciones", publicaciones);
            return "publicaciones/list"; // plantillas/publicaciones/list.html
      }

      // FORMULARIO DE CREACIÓN (GET /publicaciones/create)
      @GetMapping("/create")
      public String mostrarFormularioCrearPublicacion(Model model, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }
            model.addAttribute("publicacion", new Publicacion()); // OJO: cambia a singular, más claro para el form
            return "publicaciones/create"; // plantillas/publicaciones/create.html
      }

      // GUARDAR PUBLICACIÓN (POST /publicaciones/create)
      @PostMapping("/create")
      public String crearPublicacion(HttpServletRequest request, HttpSession sesion) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuario");
            if (usuarioActual == null) {
                  return "redirect:/login";
            }

            Publicacion publicacionACrear;

            if (request.getParameter("estado") != null) {
                  Bien bien = new Bien();
                  bien.setEstado(request.getParameter("estado"));
                  bien.setDisponible(request.getParameter("disponible") != null);
                  bien.setAncho(Double.parseDouble(request.getParameter("ancho")));
                  bien.setAlto(Double.parseDouble(request.getParameter("alto")));
                  bien.setProfundidad(Double.parseDouble(request.getParameter("profundidad")));
                  publicacionACrear = bien;
            } else {
                  Servicio servicio = new Servicio();
                  servicio.setHoras(Integer.parseInt(request.getParameter("horas")));
                  servicio.setMinutos(Integer.parseInt(request.getParameter("minutos")));
                  publicacionACrear = servicio;
            }

            publicacionACrear.setTitulo(request.getParameter("titulo"));
            publicacionACrear.setDescripcion(request.getParameter("descripcion"));
            publicacionACrear.setPrecio(Double.parseDouble(request.getParameter("precio")));
            publicacionACrear.setUsuario(usuarioActual);
            publicacionACrear.setFecha(LocalDateTime.now());

            publicacionService.crearPublicacion(publicacionACrear);
            return "redirect:/publicaciones"; // Siempre al listado
      }

      // DETALLE DE UNA PUBLICACIÓN (GET /publicaciones/{id})
      @GetMapping("/{id}")
      public String verPublicacion(@PathVariable Long id, Model modelo) {
            Publicacion publicacion = publicacionService.encontrarPublicacion(id);

            if (publicacion != null) {
                  modelo.addAttribute("publicacion", publicacion); // OJO: en singular
                  return "publicaciones/read"; // plantillas/publicaciones/read.html
            }
            return "redirect:/publicaciones";
      }

      // FORMULARIO DE ACTUALIZACIÓN (GET /publicaciones/update/{id})
      @GetMapping("/update/{id}")
      public String mostrarFormularioActualizarPublicacion(@PathVariable Long id, Model modelo, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }
            Publicacion publicacion = publicacionService.encontrarPublicacion(id);
            modelo.addAttribute("publicacion", publicacion); // singular
            return "publicaciones/update"; // plantillas/publicaciones/update.html
      }

      // ACTUALIZAR PUBLICACIÓN (POST /publicaciones/update/{id})
      @PostMapping("/update/{id}")
      public String actualizarPublicacion(
              @PathVariable Long id,
              @ModelAttribute Publicacion publicacionAActualizar,
              HttpSession sesion
      ) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuario");
            if (usuarioActual == null) {
                  return "redirect:/login";
            }
            Publicacion publicacionOriginal = publicacionService.encontrarPublicacion(id);

            if (publicacionOriginal != null && publicacionOriginal.getUsuario().getId().equals(usuarioActual.getId())) {
                  publicacionService.actualizarPublicacion(publicacionAActualizar);
            }
            return "redirect:/publicaciones";
      }

      // ELIMINAR PUBLICACIÓN (POST /publicaciones/delete/{id})
      @PostMapping("/delete/{id}")
      public String borrarPublicacion(@PathVariable Long id, HttpSession sesion) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuario");
            if (usuarioActual != null) {
                  Publicacion publicacionAEliminar = publicacionService.encontrarPublicacion(id);
                  if (publicacionAEliminar != null && publicacionAEliminar.getUsuario().getId().equals(usuarioActual.getId())) {
                        publicacionService.eliminarPublicacion(publicacionAEliminar);
                  }
            }
            return "redirect:/publicaciones";
      }
}
