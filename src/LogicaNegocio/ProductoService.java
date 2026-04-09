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
        if (!validarProducto(producto, true)) {
            return false;
        }

        return productoDAO.agregarProducto(producto);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listarProductos();
    }

    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarProductoPorId(id);
    }

    public boolean actualizarProducto(Producto producto) {
        if (!validarProducto(producto, false)) {
            return false;
        }

        return productoDAO.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int id) {
        if (productoDAO.buscarProductoPorId(id) == null) {
            System.out.println("No existe un producto con el ID indicado.");
            return false;
        }

        return productoDAO.eliminarProducto(id);
    }

    private boolean validarProducto(Producto producto, boolean validarDuplicado) {
        if (producto.getId() <= 0) {
            System.out.println("El ID debe ser mayor que 0.");
            return false;
        }

        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return false;
        }

        if (producto.getCantidad() < 0) {
            System.out.println("La cantidad no puede ser negativa.");
            return false;
        }

        if (producto.getPrecio() < 0) {
            System.out.println("El precio no puede ser negativo.");
            return false;
        }

        Producto existente = productoDAO.buscarProductoPorId(producto.getId());
        if (validarDuplicado && existente != null) {
            System.out.println("Ya existe un producto con ese ID.");
            return false;
        }

        if (!validarDuplicado && existente == null) {
            System.out.println("No existe un producto con ese ID.");
            return false;
        }

        return true;
    }
}
