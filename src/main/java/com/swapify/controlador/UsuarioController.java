package com.swapify.controlador;

import com.swapify.modelo.Usuario;
import com.swapify.persistencia.DAOException;
import com.swapify.servicio.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {
      @Autowired
      private UsuarioService usuarioService;

      @GetMapping("/login")
      public String mostrarLogin(Model modelo){
            modelo.addAttribute("usuario", new Usuario());
            return "login";
      }

      @RequestMapping(value = "/login", method = RequestMethod.POST)
      public String login(@ModelAttribute("usuario") Usuario usuarioAAutenticar, HttpSession sesion, Model modelo){
            try {
                  Usuario usuarioAutenticado = usuarioService.autenticarUsuario(usuarioAAutenticar.getEmail(), usuarioAAutenticar.getContrasenna());

                  if (usuarioAutenticado != null) {
                        sesion.setAttribute("usuario", usuarioAutenticado);
                        return "redirect:/home";
                  } else {
                        modelo.addAttribute("error", "Credenciales incorrectas");
                        modelo.addAttribute("usuario", new Usuario());
                        return "login";
                  }
            } catch (DAOException e) {
                  modelo.addAttribute("error", e.getMessage());
                  return "login";
            }
      }

      @GetMapping("/logout")
      public String logout(HttpSession sesion) {
            sesion.invalidate();
            return "redirect:/home";
      }

      @GetMapping("/register")
      public String mostrarRegistroUsuario (Model modelo) {
            modelo.addAttribute("usuario", new Usuario());
            return "register";
      }

      @PostMapping("/register")
      public String registrarUsuario(@ModelAttribute Usuario usuario,
                                     @RequestParam("confirmacionContrasenna") String confirmacionContrasenna,
                                     Model modelo) throws DAOException {

            if(!usuario.getContrasenna().equals(confirmacionContrasenna)){
                  modelo.addAttribute("error", "Las contraseñas no coinciden");
                  return "register";
            }
            usuarioService.crearUsuario(usuario);
            return "redirect:/login";
      }

      @GetMapping("/user/profile")
      public String verPerfil(HttpSession sesion, Model modelo) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
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
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }
            modelo.addAttribute("usuario", usuario);
            return "editarPerfil";
      }

      @PostMapping("user/edit")
      public String actualizarPerfil(@ModelAttribute Usuario usuarioActualizado, HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                  return "redirect:/login";
            }
            usuarioService.actualizarUsuario(usuarioActualizado);
            sesion.setAttribute("usuarioLogueado", usuarioActualizado);
            return "redirect:/user/profile";
      }

      @PostMapping("")
      public String eliminarCuenta(HttpSession sesion) {
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario != null) {
                  usuarioService.eliminarUsuario(usuario);
                  sesion.invalidate();
            }
            return "redirect:/home";
      }
}
