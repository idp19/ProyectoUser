package es.demo.iservicio;

import es.demo.domain.Producto;
import java.util.List;

public interface IProductoServicio {

    public List<Producto> listarProducto();

    public void guardar(Producto producto);

    public void borrar(Producto producto);

    public Producto buscarProducto(Producto producto);

}
