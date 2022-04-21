package es.demo.web;

import es.demo.domain.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import es.demo.iservicio.IProyectoServicio;
import org.springframework.security.access.annotation.Secured;

@Controller
public class ControladorProyecto {

    @Autowired
    private IProyectoServicio proyectoServicio;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/listarProyecto")
    public String inicio(Model model) {
        //traemos los datos de tabla proyecto y los cargamos en el model
        var proyecto = proyectoServicio.listarProyecto();
        model.addAttribute("proyecto", proyecto);
        //la salida va a la pagina ListadoProyecto.html
        return "ListadoProyecto";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/agregarProyecto")
    public String agregar(Proyecto proyecto) {
        //Devolvemos la  pagina InsertarProyecto.html
        return "InsertarProyecto";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/guardarProyecto")
    public String guardar(Proyecto proyecto) {
        //Guardamos los datos y redirigimos al metodo listarProyecto
        //Para mostrar el listado actualizado
        proyectoServicio.guardar(proyecto);
        return "redirect:/listarProyecto";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editarProyecto/{ID_PROYECTO}")
    public String editar(Proyecto proyecto, Model model) {
        //Buscamos el proyecto por el id pasado por parametro y lo añadimos al modelo
        proyecto = proyectoServicio.buscarProyecto(proyecto);
        model.addAttribute("proyecto", proyecto);
        //Pasamos los datos a la pagina EditarProyecto.html
        return "EditarProyecto";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/eliminarProyecto/{ID_PROYECTO}")
    public String eliminar(Proyecto proyecto) {
        //Eliminamos el proyecto por el id pasado por parametro
        proyectoServicio.borrar(proyecto);
        return "redirect:/listarProyecto";
    }
}
