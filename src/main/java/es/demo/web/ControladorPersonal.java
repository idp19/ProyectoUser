package es.demo.web;

import com.lowagie.text.DocumentException;
import es.demo.domain.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import es.demo.iservicio.IPersonalServicio;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.annotation.Secured;
import util.ExportarExcelPersonal;
import util.ExportarPdfPersonal;

@Controller
public class ControladorPersonal {

    @Autowired
    private IPersonalServicio personalServicio;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/listarPersonal")
    public String inicio(Model model) {
        //traemos todos los datos de personal
        List<Personal> personal = personalServicio.listarPersonal();
        model.addAttribute("personal", personal);
        //retornamos a la página ListadoPersonal.html
        return "ListadoPersonal";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/agregarPersonal")
    public String agregar(Personal personal) {
        //redirigimos a la página InsertarPersonal.html
        return "InsertarPersonal";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/guardarPersonal")
    public String guardarForm(Model model) {
        //añadimos al modelo un nuevo objeto Personal
        model.addAttribute("personal", new Personal());
        return "guardar";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/guardarPersonal")
    public String guardar(@Valid @ModelAttribute Personal personal, BindingResult Result, Model model) {
        //recogemos los datos de tipo personal y los guardamos 
        model.addAttribute("personal", personalServicio.guardar(personal));
        //redirigimos al metodo listarPersonal para mostrar los datos actualizados
        return "redirect:/listarPersonal";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/eliminarPersonal/{ID_PERSONAL}")
    public String eliminar(Personal personal) {
        //buscamos el registro de personal por el id pasado por parámetro
        personalServicio.borrar(personal);
        //redirigimos al método listarPersonal para mostrar los datos actualizados
        return "redirect:/listarPersonal";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/exportarPdf")
    public void exportarPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        //formato para la fecha
        DateFormat fechaformato = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = fechaformato.format(new Date());
        //se indica la configuracion del archivo de salida
        String headerKey = "Content-Disposition";
        //se asigna nombre al archivo de salida
        String headerValue = "attachment; filename=Listado_personal-" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        //traemos los datos del listado personal
        List<Personal> personal = personalServicio.listarPersonal();
        //creamos un nuevo objeto del tipo exportarpdf situado en la carpeta util
        ExportarPdfPersonal exportar = new ExportarPdfPersonal(personal);
        exportar.export(response);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/exportarExcel")
    public void exportaraExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        //formato para la fecha
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());
        //se indica la configuracion del archivo de salida
        String headerKey = "Content-Disposition";
        //se asigna nombre al archivo de salida
        String headerValue = "attachment; filename=Listado_personal-" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        //traemos los datos del listado personal
        List<Personal> Personal = personalServicio.listarPersonal();
        //creamos un nuevo objeto del tipo exportar excel situado en la carpeta util
        ExportarExcelPersonal generator = new ExportarExcelPersonal(Personal);
        generator.generate(response);
    }
}
