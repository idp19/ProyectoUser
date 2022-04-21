package es.demo.servicio;

import es.demo.iservicio.IProductoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.demo.dao.ProductoDao;
import es.demo.domain.Producto;

@Service
public class ProductoServicioImpl implements IProductoServicio {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProducto() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void borrar(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscarProducto(Producto producto) {
        return productoDao.findById(producto.getID_PRODUCTO()).orElse(null);
    }

}
