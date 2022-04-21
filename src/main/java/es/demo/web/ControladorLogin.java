package es.demo.web;

import es.demo.domain.Login;
import es.demo.iservicio.ILoginServicio;
import es.demo.iservicio.IPerfilServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorLogin {

    @Autowired
    private ILoginServicio loginServicio;

    @Autowired
    private IPerfilServicio perfilServicio;

    @Secured("ROLE_ADMIN")
    @PostMapping("/guardarLoginEditar")
    public String guardarEditar(Login login) {
        loginServicio.guardarEditar(login);
        return "redirect:/listarPersonal";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editarLogin/{ID_PERSONAL}")
    public String editar(Login login, Model model) {
        login = loginServicio.buscarLogin(login);
        var perfil = perfilServicio.listarPerfil();
        model.addAttribute("login", login);        
        model.addAttribute("perfil", perfil);
        return "EditarLogin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
