# Manual de Usuario

## 1. Descripcion del sistema

`SistemaInventarioQuiz` es una aplicacion de consola desarrollada en Java para gestionar productos de un inventario.

El sistema permite:

- Registrar productos nuevos.
- Listar todos los productos almacenados.
- Buscar un producto por su identificador.
- Actualizar la informacion de un producto existente.
- Eliminar productos del inventario.

La informacion se guarda en el archivo `productos.txt`, por lo que los datos permanecen disponibles entre ejecuciones.

## 2. Como ejecutar el sistema

### Opcion 1: desde NetBeans

1. Abrir el proyecto `SistemaInventarioQuiz` en NetBeans.
2. Verificar que la clase principal configurada sea `presentacion.Main`.
3. Ejecutar el proyecto con la opcion `Run Project`.

### Opcion 2: desde terminal

1. Ubicarse en la carpeta del proyecto.
2. Compilar el proyecto.
3. Ejecutar la clase principal `presentacion.Main`.

Ejemplo general:

```bash
javac -d build/classes src/Entidades/Producto.java src/DAO/ProductoDAO.java src/DAO/ProductoDAOImpl.java src/LogicaNegocio/ProductoService.java src/Presentacion/Main.java
java -cp build/classes presentacion.Main
```

Nota:
- La forma exacta de compilacion puede variar segun el entorno.
- Si se usa NetBeans, la compilacion y ejecucion se realizan automaticamente.

## 3. Explicacion del menu

Al ejecutar el sistema se muestra el siguiente menu principal:

```text
===== SISTEMA DE INVENTARIO =====
1. Registrar producto
2. Listar productos
3. Buscar producto por ID
4. Actualizar producto
5. Eliminar producto
0. Salir
```

### Opcion 1. Registrar producto

Solicita:

- ID
- Nombre
- Cantidad
- Precio

Si los datos son validos, el producto se guarda en el archivo.

### Opcion 2. Listar productos

Muestra todos los productos registrados en el inventario.

### Opcion 3. Buscar producto por ID

Solicita el ID del producto y muestra su informacion si existe.

### Opcion 4. Actualizar producto

Solicita el ID del producto a modificar y luego pide:

- Nuevo nombre
- Nueva cantidad
- Nuevo precio

Si el producto existe, se reemplaza su informacion anterior.

### Opcion 5. Eliminar producto

Solicita el ID del producto y lo elimina del archivo si existe.

### Opcion 0. Salir

Finaliza la ejecucion del programa.

## 4. Validaciones que realiza el sistema

El sistema controla:

- El ID debe ser mayor que 0.
- El nombre no puede estar vacio.
- La cantidad no puede ser negativa.
- El precio no puede ser negativo.
- No se permite registrar dos productos con el mismo ID.
- No se puede actualizar ni eliminar un producto inexistente.
- Si el usuario escribe letras en lugar de numeros, el sistema vuelve a pedir el dato.

## 5. Ejemplos de uso

### Ejemplo 1. Registrar un producto

```text
Seleccione una opcion: 1
Ingrese ID: 101
Ingrese nombre: Laptop
Ingrese cantidad: 5
Ingrese precio: 450000
Producto registrado correctamente.
```

### Ejemplo 2. Listar productos

```text
Seleccione una opcion: 2

--- LISTA DE PRODUCTOS ---
ID: 101 | Nombre: Laptop | Cantidad: 5 | Precio: 450000.0
```

### Ejemplo 3. Buscar un producto existente

```text
Seleccione una opcion: 3
Ingrese ID del producto: 101
Producto encontrado:
ID: 101 | Nombre: Laptop | Cantidad: 5 | Precio: 450000.0
```

### Ejemplo 4. Intentar registrar un ID duplicado

```text
Seleccione una opcion: 1
Ingrese ID: 101
Ingrese nombre: Mouse
Ingrese cantidad: 10
Ingrese precio: 8000
Ya existe un producto con ese ID.
No se pudo registrar el producto.
```

### Ejemplo 5. Actualizar un producto

```text
Seleccione una opcion: 4
Ingrese ID del producto a actualizar: 101
Ingrese nuevo nombre: Laptop Gamer
Ingrese nueva cantidad: 4
Ingrese nuevo precio: 525000
Producto actualizado correctamente.
```

### Ejemplo 6. Eliminar un producto

```text
Seleccione una opcion: 5
Ingrese ID del producto a eliminar: 101
Producto eliminado correctamente.
```
