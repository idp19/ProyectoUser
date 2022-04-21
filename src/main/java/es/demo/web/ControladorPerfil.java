package es.demo.web;

import es.demo.domain.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import es.demo.iservicio.IPerfilServicio;
import org.springframework.security.access.annotation.Secured;

@Controller
public class ControladorPerfil {

    @Autowired
    private IPerfilServicio perfilServicio;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/listarPerfil")
    public String inicio(Model model) {
        var perfil = perfilServicio.listarPerfil();
        model.addAttribute("perfil", perfil);
        return "ListadoPerfil";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/agregarPerfil")
    public String agregar(Perfil perfil) {
        return "InsertarPerfil";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/guardarPerfil")
    public String guardar(Perfil perfil) {
        perfilServicio.guardar(perfil);
        return "redirect:/listarPerfil";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editarPerfil/{ID_PERFIL}")
    public String editar(Perfil perfil, Model model) {
        perfil = perfilServicio.buscarPerfil(perfil);
        model.addAttribute("perfil", perfil);
        return "EditarPerfil";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/eliminarPerfil/{ID_PERFIL}")
    public String eliminar(Perfil perfil) {
        perfilServicio.borrar(perfil);
        return "redirect:/listarPerfil";
    }
}
