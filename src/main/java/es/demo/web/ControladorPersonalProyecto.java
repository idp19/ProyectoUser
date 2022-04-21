package es.demo.web;

import es.demo.domain.Personal;
import es.demo.domain.Proyecto;
import es.demo.domain.ProyectoPersona;
import es.demo.iservicio.IPersonalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.demo.iservicio.IProyectoPersonaServicio;
import es.demo.iservicio.IProyectoServicio;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ControladorPersonalProyecto {

    @Autowired
    private IProyectoPersonaServicio proyectoPersonalServicio;
    
    @Autowired
    private IPersonalServicio personalServicio;

    @Autowired
    private IProyectoServicio proyectoServicio;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/verProyecto/{ID_PROYECTO}")
    public String ver(Proyecto proyecto, Model model, @PathVariable int ID_PROYECTO) {
        //Traemos los datos del proyecto con el id pasado por parámetro
        proyecto = proyectoServicio.buscarProyecto(proyecto);
        model.addAttribute("proyecto", proyecto);
        //Traemos los datos de Personal que estén en el proyecto
        List<Personal> personal = personalServicio.personasEnProyecto(ID_PROYECTO);
        model.addAttribute("personal", personal);
        //Añadido todo al model retornamos la página VistaProyecto.html
        return "VistaProyecto";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/listarPersonalProyecto/{NOM_PROYECTO}/{ID_PROYECTO}")
    public String verPersonal(Model model, @PathVariable String NOM_PROYECTO, @PathVariable int ID_PROYECTO) {
        //Traemos listado de personal que no esten en el proyecto con id pasado por parámetro
        List<Personal> personal = personalServicio.personasDisponibles(ID_PROYECTO);
        model.addAttribute("personal", personal);
        model.addAttribute(NOM_PROYECTO);
        model.addAttribute(ID_PROYECTO);
        //Una vez añadidos al modelo retornamos la página ListadoPersonalProyecto.html
        return "ListadoPersonalProyecto";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/guardarPersonalProyecto/{ID_PERSONAL}/{ID_PROYECTO}")
    public String guardarPersonalProyecto(Model model, @PathVariable int ID_PERSONAL, @PathVariable int ID_PROYECTO) {
        //Creamos un nuevo objeto ProyectoPersona para setear los id
        ProyectoPersona proyectoPersona = new ProyectoPersona();
        proyectoPersona.setID_PERSONAL(ID_PERSONAL);
        proyectoPersona.setID_PROYECTO(ID_PROYECTO);
        proyectoPersonalServicio.guardar(proyectoPersona);
        //Una vez guardado redirigimos al método verProyecto pasandole tambien el id del proyecto
        return "redirect:/verProyecto/{ID_PROYECTO}";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/eliminarProyectoPersonal/{ID_PERSONAL}/{ID_PROYECTO}")
    public String eliminar(Model model, @PathVariable int ID_PERSONAL, @PathVariable int ID_PROYECTO) {
        //Cogemos los datos pasados por parametro 
        model.addAttribute(ID_PERSONAL);
        model.addAttribute(ID_PROYECTO);
        //Usamos borrarProyectoPersona para eliminar
        proyectoPersonalServicio.borrarProyectoPersona(ID_PERSONAL);
        return "redirect:/verProyecto/{ID_PROYECTO}";
    }

}
