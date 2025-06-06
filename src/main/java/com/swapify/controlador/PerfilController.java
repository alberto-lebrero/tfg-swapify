package com.swapify.controlador;

import com.swapify.modelo.Perfil;
import com.swapify.modelo.Usuario;
import com.swapify.persistencia.DAOException;
import com.swapify.servicio.PerfilService;
import com.swapify.servicio.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/profile")
public class PerfilController {

      @Autowired
      private PerfilService perfilService;

      @Autowired
      private UsuarioService usuarioService;

      @GetMapping("/read")
      public String verPerfil(HttpSession sesion, Model modelo) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }

            try {
                  Perfil perfil = perfilService.encontrarPerfilDeUsuario(usuario.getId());
                  //modelo.addAttribute("usuario", usuario);
                  if (perfil == null) {
                        return "redirect:/user/profile/create";
                  }
                  modelo.addAttribute("perfil", perfil);
                  return "user/profile/read";
            } catch (DAOException e) {
                  modelo.addAttribute("error", "No se pudo cargar el perfil del usuario");
                  return "error";
            }
      }

      // Mostrar formulario de edición
      @GetMapping("/update")
      public String mostrarFormularioEdicion(HttpSession sesion, Model modelo) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) return "redirect:/login";

            modelo.addAttribute("usuario", usuario);
            return "user/profile/update";
      }

      @GetMapping("/create")
      public String mostrarFormularioCrearPerfil(Model modelo, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) return "redirect:/login";

            if (usuario.getPerfil() != null) {
                  return "redirect:/user/profile/read";
            }

            modelo.addAttribute("usuario", usuario);
            return "user/profile/create";
      }

      @PostMapping("/create")
      public String crearPerfil(@ModelAttribute("usuario") Usuario usuarioFormulario, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }

            Perfil nuevoPerfil = usuarioFormulario.getPerfil();

            nuevoPerfil.setUsuario(usuario);

            perfilService.crearPerfil(nuevoPerfil);
            usuario.setPerfil(nuevoPerfil);

            usuarioService.actualizarUsuario(usuario);

            return "redirect:/user/profile/read";
      }


      @PostMapping("/update")
      public String actualizarPerfil(@ModelAttribute("usuario") Usuario usuarioForm, HttpSession sesion) {
            Usuario usuarioSesion = (Usuario) sesion.getAttribute("usuario");
            if (usuarioSesion == null) return "redirect:/login";

            if (usuarioSesion.getPerfil() == null) {
                  return "redirect:/user/profile/create"; // redirigir si aún no tiene perfil
            }

            Perfil perfilExistente = usuarioSesion.getPerfil();
            Perfil datosForm = usuarioForm.getPerfil();

            // Validación y actualización campo a campo
            if (datosForm.getNif() != null && !datosForm.getNif().isBlank()) {
                  perfilExistente.setNif(datosForm.getNif());
            }
            if (datosForm.getNombre() != null && !datosForm.getNombre().isBlank()) {
                  perfilExistente.setNombre(datosForm.getNombre());
            }
            if (datosForm.getPrimerApellido() != null && !datosForm.getPrimerApellido().isBlank()) {
                  perfilExistente.setPrimerApellido(datosForm.getPrimerApellido());
            }
            if (datosForm.getSegundoApellido() != null && !datosForm.getSegundoApellido().isBlank()) {
                  perfilExistente.setSegundoApellido(datosForm.getSegundoApellido());
            }
            if (datosForm.getTelefono() != null && !datosForm.getTelefono().isBlank()) {
                  perfilExistente.setTelefono(datosForm.getTelefono());
            }
            if (datosForm.getDescripcion() != null && !datosForm.getDescripcion().isBlank()) {
                  perfilExistente.setDescripcion(datosForm.getDescripcion());
            }
            if (datosForm.getDireccion() != null && !datosForm.getDireccion().isBlank()) {
                  perfilExistente.setDireccion(datosForm.getDireccion());
            }
            if (datosForm.getProvincia() != null && !datosForm.getProvincia().isBlank()) {
                  perfilExistente.setProvincia(datosForm.getProvincia());
            }
            if (datosForm.getCodigoPostal() != null && !datosForm.getCodigoPostal().isBlank()) {
                  perfilExistente.setCodigoPostal(datosForm.getCodigoPostal());
            }
            if (datosForm.getPais() != null && !datosForm.getPais().isBlank()) {
                  perfilExistente.setPais(datosForm.getPais());
            }

            perfilService.actualizarPerfil(perfilExistente);

            return "redirect:/user/profile/read";
      }
}
