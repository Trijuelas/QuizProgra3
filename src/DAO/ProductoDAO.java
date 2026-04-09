package dao;

import entidades.Producto;
import java.util.List;

public interface ProductoDAO {

    boolean agregarProducto(Producto producto);

    List<Producto> listarProductos();

    Producto buscarProductoPorId(int id);
}
