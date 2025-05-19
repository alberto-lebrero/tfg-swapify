package com.swapify.controlador;

import com.swapify.modelo.Usuario;
import com.swapify.persistencia.DAOException;
import com.swapify.servicio.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
      @Autowired
      private UsuarioService usuarioService;

      /*@GetMapping("/home")
      public String inicio(Model modelo, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
            modelo.addAttribute("usuario", usuario);
            return "home";
      }*/

      @GetMapping("/login")
      public String mostrarFormularioLogin(Model modelo){
            modelo.addAttribute("usuario", new Usuario());
            return "login";
      }

      @PostMapping("/login")
      public String login(@ModelAttribute("usuario") Usuario usuarioFormulario, HttpSession sesion, Model modelo){
            Usuario usuario = usuarioService.autenticar(usuarioFormulario.getEmail(), usuarioFormulario.getContrasenna());
            if (usuario != null) {
                  sesion.setAttribute("usuarioLogueado", usuario);
                  return "redirect:/home";
            } else {
                  modelo.addAttribute("error", "Credemciales incorrectas");
                  return "login";
            }
      }

      @GetMapping("/logout")
      public String logout(HttpSession sesion) {
            sesion.invalidate();
            return "redirect:/home";
      }

      @GetMapping("/register")
      public String m (Model modelo) {
            modelo.addAttribute("usuario", new Usuario());
            return "register";
      }

      @PostMapping("/register")
      public String registrarUsuario(@ModelAttribute Usuario usuario, Model modelo) throws DAOException {
            usuarioService.crearUsuario(usuario);
            return "redirect:/login";
      }

      @GetMapping("/user/profile")
      public String verPerfil(HttpSession sesion, Model modelo) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
            if (usuario == null) {
                  return "redirect:/login";
            }
            modelo.addAttribute("usuario", usuario);
            return "profile";
      }
      /**
       * Repasar
       */
      @GetMapping("/user/profile/edit")
      public String editarPerfil(HttpSession sesion, Model modelo) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
            if (usuario == null) {
                  return "redirect:/login";
            }
            modelo.addAttribute("usuario", usuario);
            return "editarPerfil";
      }

      @PostMapping("user/edit")
      public String actualizarPerfil(@ModelAttribute Usuario usuarioActualizado, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
            if (usuario == null) {
                  return "redirect:/login";
            }
            usuarioService.actualizarUsuario(usuarioActualizado);
            sesion.setAttribute("usuarioLogueado", usuarioActualizado);
            return "redirect:/user/profile";
      }

      @PostMapping("")
      public String eliminarCuenta(HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
            if (usuario != null) {
                  usuarioService.eliminarUsuario(usuario);
                  sesion.invalidate();
            }
            return "redirect:/home";
      }
}
