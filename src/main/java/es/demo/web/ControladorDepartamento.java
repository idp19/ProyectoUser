package es.demo.web;

import es.demo.domain.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import es.demo.iservicio.IDepartamentoServicio;
import org.springframework.security.access.annotation.Secured;

@Controller
public class ControladorDepartamento {

    @Autowired
    private IDepartamentoServicio departamentoServicio;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/listarDepartamento")
    public String inicio(Model model) {
        var departamento = departamentoServicio.listarDepartamento();
        model.addAttribute("departamento", departamento);
        return "ListadoDepartamento";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/agregarDepartamento")
    public String agregar(Departamento departamento) {
        return "InsertarDepartamento";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/guardarDepartamento")
    public String guardar(Departamento departamento) {
        departamentoServicio.guardar(departamento);
        return "redirect:/listarDepartamento";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editarDepartamento/{ID_DEPARTAMENTO}")
    public String editar(Departamento departamento, Model model) {
        departamento = departamentoServicio.buscarDepartamento(departamento);
        model.addAttribute("departamento", departamento);
        return "EditarDepartamento";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/eliminarDepartamento/{ID_DEPARTAMENTO}")
    public String eliminar(Departamento departamento) {
        departamentoServicio.borrar(departamento);
        return "redirect:/listarDepartamento";
    }
}
