package es.demo.web;

import es.demo.domain.PersonalEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import es.demo.iservicio.IPersonalEditServicio;
import es.demo.iservicio.IPerfilServicio;
import org.springframework.security.access.annotation.Secured;

@Controller
public class ControladorPersonalEdit {

    @Autowired
    private IPersonalEditServicio personalEditServicio;

    @Autowired
    private IPerfilServicio perfilServicio;

    @Secured("ROLE_ADMIN")
    @PostMapping("/guardarPersonalEditar")
    public String guardarEditar(PersonalEdit personaledit) {
        //Recogemos los datos y los guardamos
        personalEditServicio.guardarEditar(personaledit);
        //redirigimos al método listarPersonal 
        return "redirect:/listarPersonal";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editarPersonal/{ID_PERSONAL}")
    public String editar(PersonalEdit personaledit, Model model) {
        //traemos los datos de personalEdit por si id pasado por parámetro
        personaledit = personalEditServicio.buscarPersonal(personaledit);
        //traemos los datos de perfiles 
        var perfil = perfilServicio.listarPerfil();
        model.addAttribute("personaledit", personaledit);
        model.addAttribute("perfil", perfil);
        //retornamos la página EditarPersonal.html
        return "EditarPersonal";
    }

}
