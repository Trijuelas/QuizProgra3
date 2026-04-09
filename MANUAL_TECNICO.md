# Manual Tecnico

## 1. Arquitectura por capas

El sistema fue construido con una arquitectura por capas para separar responsabilidades y facilitar el mantenimiento.

Capas implementadas:

- `Presentacion`: se encarga de la interaccion con el usuario por consola.
- `LogicaNegocio`: aplica reglas de validacion y controla las operaciones del sistema.
- `DAO`: administra el acceso a los datos almacenados en archivo plano.
- `Entidades`: define los objetos del dominio del sistema.

Esta separacion permite que cada capa tenga una funcion especifica y reduce el acoplamiento entre componentes.

## 2. Responsabilidad de cada clase

### `Producto`

Ubicacion: `src/Entidades/Producto.java`

Responsabilidades:

- Representar un producto del inventario.
- Almacenar los atributos `id`, `nombre`, `cantidad` y `precio`.
- Convertir el objeto a formato de texto mediante `toFileString()`.
- Mostrar una representacion legible con `toString()`.

### `ProductoDAO`

Ubicacion: `src/DAO/ProductoDAO.java`

Responsabilidades:

- Definir el contrato de acceso a datos.
- Declarar las operaciones CRUD:
  `agregarProducto`, `listarProductos`, `buscarProductoPorId`, `actualizarProducto`, `eliminarProducto`.

### `ProductoDAOImpl`

Ubicacion: `src/DAO/ProductoDAOImpl.java`

Responsabilidades:

- Implementar la interfaz `ProductoDAO`.
- Crear el archivo `productos.txt` si no existe.
- Guardar productos en archivo plano.
- Leer y convertir lineas del archivo en objetos `Producto`.
- Actualizar o eliminar registros reescribiendo el archivo.

### `ProductoService`

Ubicacion: `src/LogicaNegocio/ProductoService.java`

Responsabilidades:

- Coordinar las operaciones del inventario.
- Aplicar validaciones de negocio antes de llamar al DAO.
- Verificar duplicados al registrar.
- Verificar existencia al actualizar y eliminar.

### `Main`

Ubicacion: `src/Presentacion/Main.java`

Responsabilidades:

- Mostrar el menu principal.
- Leer datos desde teclado con `Scanner`.
- Invocar los metodos del servicio segun la opcion elegida.
- Manejar errores de entrada con `leerEntero()` y `leerDouble()`.

## 3. Flujo del sistema

El flujo general del sistema es el siguiente:

1. La clase `Main` inicia el programa.
2. `Main` crea una instancia de `ProductoDAOImpl`.
3. `Main` crea una instancia de `ProductoService` y le inyecta el DAO.
4. El usuario selecciona una opcion del menu.
5. `Main` recopila los datos necesarios.
6. `ProductoService` valida los datos.
7. Si la validacion es correcta, `ProductoService` llama al `ProductoDAO`.
8. `ProductoDAOImpl` lee o escribe en `productos.txt`.
9. El resultado regresa a la capa de presentacion y se muestra al usuario.

## 4. Flujo por operacion

### Registro de producto

`Main -> ProductoService.registrarProducto() -> ProductoDAOImpl.agregarProducto() -> productos.txt`

### Listado de productos

`Main -> ProductoService.listarProductos() -> ProductoDAOImpl.listarProductos() -> productos.txt`

### Busqueda por ID

`Main -> ProductoService.buscarProductoPorId() -> ProductoDAOImpl.buscarProductoPorId()`

### Actualizacion

`Main -> ProductoService.actualizarProducto() -> ProductoDAOImpl.actualizarProducto() -> productos.txt`

### Eliminacion

`Main -> ProductoService.eliminarProducto() -> ProductoDAOImpl.eliminarProducto() -> productos.txt`

## 5. Decisiones tecnicas tomadas

### Uso de archivo plano

Se utilizo `productos.txt` como mecanismo de persistencia porque:

- Es simple de implementar.
- No requiere configurar una base de datos.
- Es adecuado para un proyecto academico pequeno.

### Uso de interfaz DAO

Se creo la interfaz `ProductoDAO` para desacoplar la logica de negocio de la implementacion concreta del almacenamiento.

Ventajas:

- Facilita el mantenimiento.
- Permite cambiar la fuente de datos en el futuro.
- Mejora la organizacion del proyecto.

### Validaciones en la capa de negocio

Las validaciones se centralizaron en `ProductoService` para evitar que la capa de presentacion o la capa DAO mezclen responsabilidades de negocio.

### Manejo de errores de entrada

Se agregaron los metodos `leerEntero()` y `leerDouble()` en `Main` para evitar que el programa falle cuando el usuario ingresa datos no numericos.

### Reescritura completa del archivo en actualizacion y eliminacion

Para modificar o borrar productos se lee el contenido actual y luego se reescribe el archivo completo.

Esta decision se tomo porque:

- Es un enfoque sencillo para archivo plano.
- Evita trabajar con posiciones especificas dentro del archivo.
- Resulta suficiente para el tamano del proyecto.

## 6. Posibles mejoras futuras

- Sustituir el archivo plano por una base de datos.
- Separar las utilidades de lectura en una clase auxiliar.
- Agregar pruebas unitarias.
- Incorporar una interfaz grafica.
- Normalizar completamente los nombres de paquetes para mantener una convension uniforme.
