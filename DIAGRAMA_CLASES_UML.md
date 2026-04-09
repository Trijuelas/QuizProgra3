# Diagrama de Clases UML

## 1. Descripcion general

El siguiente diagrama representa la estructura del sistema por capas:

- Capa de presentacion.
- Capa de logica de negocio.
- Capa de acceso a datos.
- Capa de entidades.

Tambien se muestran:

- Las relaciones de dependencia.
- La interfaz utilizada por la capa DAO.
- La implementacion concreta de la persistencia.

## 2. Diagrama UML

```mermaid
classDiagram
    class Main {
        +main(args: String[]) void
        +registrarProducto(scanner, productoService) void
        +listarProductos(productoService) void
        +buscarProducto(scanner, productoService) void
        +actualizarProducto(scanner, productoService) void
        +eliminarProducto(scanner, productoService) void
        -leerEntero(scanner, mensaje) int
        -leerDouble(scanner, mensaje) double
    }

    class ProductoService {
        -productoDAO: ProductoDAO
        +ProductoService(productoDAO: ProductoDAO)
        +registrarProducto(producto: Producto) boolean
        +listarProductos() List~Producto~
        +buscarProductoPorId(id: int) Producto
        +actualizarProducto(producto: Producto) boolean
        +eliminarProducto(id: int) boolean
        -validarProducto(producto: Producto, validarDuplicado: boolean) boolean
    }

    class ProductoDAO {
        <<interface>>
        +agregarProducto(producto: Producto) boolean
        +listarProductos() List~Producto~
        +buscarProductoPorId(id: int) Producto
        +actualizarProducto(producto: Producto) boolean
        +eliminarProducto(id: int) boolean
    }

    class ProductoDAOImpl {
        -archivo: String
        +ProductoDAOImpl()
        +agregarProducto(producto: Producto) boolean
        +listarProductos() List~Producto~
        +buscarProductoPorId(id: int) Producto
        +actualizarProducto(producto: Producto) boolean
        +eliminarProducto(id: int) boolean
    }

    class Producto {
        -id: int
        -nombre: String
        -cantidad: int
        -precio: double
        +Producto()
        +Producto(id: int, nombre: String, cantidad: int, precio: double)
        +getId() int
        +setId(id: int) void
        +getNombre() String
        +setNombre(nombre: String) void
        +getCantidad() int
        +setCantidad(cantidad: int) void
        +getPrecio() double
        +setPrecio(precio: double) void
        +toFileString() String
        +toString() String
    }

    Main --> ProductoService : usa
    ProductoService --> ProductoDAO : depende de
    ProductoDAOImpl ..|> ProductoDAO : implementa
    ProductoService --> Producto : valida y procesa
    ProductoDAOImpl --> Producto : crea y persiste
```

## 3. Interpretacion del diagrama

### Capa de Presentacion

- `Main` interactua directamente con el usuario.
- No accede al archivo de datos de forma directa.

### Capa de Logica de Negocio

- `ProductoService` recibe solicitudes desde `Main`.
- Valida datos antes de delegar las operaciones.

### Capa DAO

- `ProductoDAO` define las operaciones necesarias.
- `ProductoDAOImpl` implementa el acceso real a `productos.txt`.

### Capa de Entidades

- `Producto` representa el objeto central del sistema.

## 4. Relaciones principales

- `Main` depende de `ProductoService`.
- `ProductoService` depende de la abstraccion `ProductoDAO`.
- `ProductoDAOImpl` implementa la interfaz `ProductoDAO`.
- `ProductoService` y `ProductoDAOImpl` trabajan con objetos `Producto`.
