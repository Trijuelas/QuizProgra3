package LogicaNegocio;

import dao.ProductoDAO;
import entidades.Producto;
import java.util.List;

public class ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public boolean registrarProducto(Producto producto) {
        return productoDAO.agregarProducto(producto);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listarProductos();
    }

    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarProductoPorId(id);
    }

    public boolean actualizarProducto(Producto producto) {
        return productoDAO.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int id) {
        return productoDAO.eliminarProducto(id);
    }
}
