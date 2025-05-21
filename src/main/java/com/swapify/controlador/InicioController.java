package com.swapify.controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
      @GetMapping("/")
      public String inicio() {
            return "redirect:/home";
      }

      @GetMapping("home")
      public String home(Model model, HttpSession sesion) {
            model.addAttribute("usuario", sesion.getAttribute("usuario"));
            return "home";
      }
}
