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
                        return "redirect:/publicaciones";
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


}
