package es.demo.web;

import es.demo.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import es.demo.iservicio.IProductoServicio;
import org.springframework.security.access.annotation.Secured;

@Controller
public class ControladorProducto {

    @Autowired
    private IProductoServicio productoServicio;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/listarProducto")
    public String inicio(Model model) {
        //traemos los datos de tabla producto y los cargamos en el model
        var producto = productoServicio.listarProducto();
        //la salida va a la pagina ListadoProducto.html
        model.addAttribute("producto", producto);
        return "ListadoProducto";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/agregarProducto")
    public String agregar(Producto producto) {
        //Devolvemos la  pagina InsertarProducto.html
        return "InsertarProducto";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/guardarProducto")
    public String guardar(Producto producto) {
        //Guardamos los datos y redirigimos al metodo listarProducto
        //Para mostrar el listado actualizado
        productoServicio.guardar(producto);
        return "redirect:/listarProducto";
    }

    @GetMapping("/editarProducto/{ID_PRODUCTO}")
    public String editar(Producto producto, Model model) {
        //Buscamos el producto por el id pasado por parametro y lo añadimos al modelo
        producto = productoServicio.buscarProducto(producto);
        model.addAttribute("producto", producto);
        //Pasamos los datos a la pagina EditarProducto.html
        return "EditarProducto";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/eliminarProducto/{ID_PRODUCTO}")
    public String eliminar(Producto producto) {
        //Eliminamos el producto por el id pasado por parametro
        productoServicio.borrar(producto);
        return "redirect:/listarProducto";
    }
}
