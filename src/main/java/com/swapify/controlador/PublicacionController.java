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

      @GetMapping("")
      public String verPublicaciones(@RequestParam(value = "titulo", required = false) String titulo,
                                     Model modelo, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            List<Publicacion> publicaciones = publicacionService.buscarPublicaciones(titulo);
            modelo.addAttribute("publicaciones", publicaciones);
            modelo.addAttribute("usuario", usuario);
            return "publicaciones/list";
      }

      @GetMapping("/read/{id}")
      public String verPublicacion(@PathVariable Long id, HttpSession sesion, Model modelo) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            Publicacion publicacion = publicacionService.encontrarPublicacion(id);
            modelo.addAttribute("publicacion", publicacion);
            modelo.addAttribute("usuario", usuario);
            return "publicaciones/read";
      }


      @GetMapping("/create")
      public String mostrarFormularioCrearPublicacion(Model model, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }
            model.addAttribute("publicacion", new Publicacion());
            return "publicaciones/create";
      }

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
            return "redirect:/publicaciones";
      }

      @GetMapping("/{id}")
      public String verPublicacion(@PathVariable Long id, Model modelo) {
            Publicacion publicacion = publicacionService.encontrarPublicacion(id);

            if (publicacion != null) {
                  modelo.addAttribute("publicacion", publicacion);
                  return "publicaciones/read";
            }
            return "redirect:/publicaciones";
      }

      @GetMapping("/update/{id}")
      public String mostrarFormularioActualizarPublicacion(@PathVariable Long id, Model modelo, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }
            Publicacion publicacion = publicacionService.encontrarPublicacion(id);
            // Solo permito editar una publicación si es el usuario logueado
            if (publicacion == null || !publicacion.getUsuario().getId().equals(usuario.getId())) {
                  return "redirect:/publicaciones";
            }

            // Binding de herencia
            if (publicacion instanceof Bien) {
                  modelo.addAttribute("publicacion", (Bien) publicacion);
            } else if (publicacion instanceof Servicio) {
                  modelo.addAttribute("publicacion", (Servicio) publicacion);
            }

            return "publicaciones/update";
      }

      @PostMapping("/update/{id}")
      public String actualizarPublicacion(@PathVariable Long id,
                                          @ModelAttribute Publicacion publicacionAActualizar,
                                          HttpServletRequest request,
                                          HttpSession sesion) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuario");
            if (usuarioActual == null) {
                  return "redirect:/login";
            }

            Publicacion publicacion = publicacionService.encontrarPublicacion(id);

            if (publicacion != null && publicacion.getUsuario().getId().equals(usuarioActual.getId())) {
                  publicacion.setTitulo(request.getParameter("titulo"));
                  publicacion.setDescripcion(request.getParameter("descripcion"));
                  publicacion.setPrecio(Double.parseDouble(request.getParameter("precio")));

                  if (publicacion instanceof Bien bienOriginal) {
                        bienOriginal.setEstado(request.getParameter("estado"));
                        bienOriginal.setAncho(Double.parseDouble(request.getParameter("ancho")));
                        bienOriginal.setAlto(Double.parseDouble(request.getParameter("alto")));
                        bienOriginal.setProfundidad(Double.parseDouble(request.getParameter("profundidad")));
                  }
                  if (publicacion instanceof Servicio servicioOriginal) {
                        servicioOriginal.setHoras(Integer.parseInt(request.getParameter("horas")));
                        servicioOriginal.setMinutos(Integer.parseInt(request.getParameter("minutos")));
                  }

                  publicacionService.actualizarPublicacion(publicacion);
            }
            return "redirect:/publicaciones";
      }


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
