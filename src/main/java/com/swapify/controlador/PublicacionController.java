package com.swapify.controlador;

import com.swapify.modelo.Bien;
import com.swapify.modelo.Publicacion;
import com.swapify.modelo.Servicio;
import com.swapify.modelo.Usuario;
import com.swapify.servicio.PublicacionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/publicaciones")
public class PublicacionController {
      @Autowired
      private PublicacionService publicacionService;

      @GetMapping("/nueva")
      public String mostrarFormularioNuevaPublicacion(Model model, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");

            model.addAttribute("publicacion", new Publicacion());
            return "publicaciones/nueva";
      }

      @PostMapping("/nueva")
      public String crearPublicacion(HttpServletRequest request, HttpSession sesion) {
            Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioLogueado");

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

            publicacionService.crearPublicacion(publicacionACrear);
            return "redirect:/home";
      }


      // Es un proyecto MVC (Modelo Vista Controlador). API REST

      //¿? comprobar
      @GetMapping("/{id}")
      public String verPublicacion(@PathVariable Long id, Model modelo) {
            Publicacion publicacion = publicacionService.encontrarPublicacion(id);

            if(publicacion != null) {
                  modelo.addAttribute("publicacion", publicacion);
                  return "publicacion/detalle";
            }
            return "redirect:/home";
      }

      @GetMapping("")
      public String verPublicaciones(@RequestParam(value = "titulo", required = false) String titulo,
                                     @RequestParam(value = "tipos", required = false) List<String> tipos,
                                     Model modelo) {
            List<Publicacion> publicacionesAVisualizar = publicacionService.buscarPublicaciones(titulo, tipos);
            modelo.addAttribute("publicacionesAVisualizar", publicacionesAVisualizar);
            return "home";
      }

      @GetMapping("/editar/{id}")
      public String mostrarFormularioEditarPublicacion(@PathVariable Long id, Model modelo) {
            modelo.addAttribute("publicacion", publicacionService.encontrarPublicacion(id));
            return "editar_publicacion";
      }

      @PutMapping("/{id}")
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

      @DeleteMapping("/{id}")
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
