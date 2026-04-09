package presentacion;

import LogicaNegocio.ProductoService;
import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import entidades.Producto;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAOImpl();
        ProductoService productoService = new ProductoService(productoDAO);
        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE INVENTARIO =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Salir");
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarProducto(scanner, productoService);
                    break;
                case 2:
                    listarProductos(productoService);
                    break;
                case 3:
                    buscarProducto(scanner, productoService);
                    break;
                case 4:
                    actualizarProducto(scanner, productoService);
                    break;
                case 5:
                    eliminarProducto(scanner, productoService);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void registrarProducto(Scanner scanner, ProductoService productoService) {
        int id = leerEntero(scanner, "Ingrese ID: ");
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        int cantidad = leerEntero(scanner, "Ingrese cantidad: ");
        double precio = leerDouble(scanner, "Ingrese precio: ");

        Producto producto = new Producto(id, nombre, cantidad, precio);

        if (productoService.registrarProducto(producto)) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el producto.");
        }
    }

    public static void listarProductos(ProductoService productoService) {
        List<Producto> productos = productoService.listarProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public static void buscarProducto(Scanner scanner, ProductoService productoService) {
        int id = leerEntero(scanner, "Ingrese ID del producto: ");

        Producto producto = productoService.buscarProductoPorId(id);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void actualizarProducto(Scanner scanner, ProductoService productoService) {
        int id = leerEntero(scanner, "Ingrese ID del producto a actualizar: ");
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = scanner.nextLine();
        int cantidad = leerEntero(scanner, "Ingrese nueva cantidad: ");
        double precio = leerDouble(scanner, "Ingrese nuevo precio: ");

        Producto producto = new Producto(id, nombre, cantidad, precio);
        if (productoService.actualizarProducto(producto)) {
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el producto.");
        }
    }

    public static void eliminarProducto(Scanner scanner, ProductoService productoService) {
        int id = leerEntero(scanner, "Ingrese ID del producto a eliminar: ");

        if (productoService.eliminarProducto(id)) {
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el producto.");
        }
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero entero valido.");
                scanner.nextLine();
            }
        }
    }

    private static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero decimal valido.");
                scanner.nextLine();
            }
        }
    }
}
