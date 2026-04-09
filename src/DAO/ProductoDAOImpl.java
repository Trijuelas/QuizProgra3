package dao;

import entidades.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    private final String archivo = "productos.txt";

    public ProductoDAOImpl() {
        try {
            File file = new File(archivo);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Error al preparar el archivo de productos: " + e.getMessage());
        }
    }

    @Override
    public boolean agregarProducto(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(producto.toFileString());
            writer.newLine();
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length == 4) {
                    Producto producto = new Producto();
                    producto.setId(Integer.parseInt(partes[0]));
                    producto.setNombre(partes[1]);
                    producto.setCantidad(Integer.parseInt(partes[2]));
                    producto.setPrecio(Double.parseDouble(partes[3]));
                    productos.add(producto);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    @Override
    public Producto buscarProductoPorId(int id) {
        for (Producto producto : listarProductos()) {
            if (producto.getId() == id) {
                return producto;
            }
        }

        return null;
    }

    @Override
    public boolean actualizarProducto(Producto productoActualizado) {
        List<Producto> productos = listarProductos();
        boolean encontrado = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto producto : productos) {
                if (producto.getId() == productoActualizado.getId()) {
                    writer.write(productoActualizado.toFileString());
                    encontrado = true;
                } else {
                    writer.write(producto.toFileString());
                }
                writer.newLine();
            }

            return encontrado;
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarProducto(int id) {
        List<Producto> productos = listarProductos();
        boolean eliminado = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto producto : productos) {
                if (producto.getId() != id) {
                    writer.write(producto.toFileString());
                    writer.newLine();
                } else {
                    eliminado = true;
                }
            }

            return eliminado;
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}
