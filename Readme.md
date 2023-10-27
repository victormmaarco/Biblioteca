# Documentacion

## Clase GestorBiblioteca:
Esta clase es la clase principal del programa y extiende la clase JFrame, lo que la convierte en una ventana de la interfaz de usuario.

### main (método estático):

Este método es el punto de entrada principal del programa. Inicializa una instancia de la clase GestorBiblioteca y la hace visible.
Constructor GestorBiblioteca:

- El constructor de la clase GestorBiblioteca se encarga de configurar y crear la interfaz de usuario.

- `setDefaultLookAndFeelDecorated(true)`: Establece la decoración predeterminada para la ventana.

- `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`: Configura la acción por defecto al cerrar la ventana.

- `setBounds`: Define la posición y el tamaño de la ventana.

- Se crean componentes de la interfaz de usuario, como botones, etiquetas, campos de texto y tablas.

- Se definen ActionListener para los botones "Agregar" y "Buscar".

- Se crea una tabla para mostrar libros y otra tabla para mostrar registros de préstamos.

- Se carga la información de los libros desde el archivo "datos.txt" al iniciar la aplicación.

- Se definen métodos para manejar la visualización de registros de préstamo y operaciones relacionadas con los registros.

- El método buscarLibros se utiliza para buscar libros en base al término de búsqueda y el tipo de búsqueda seleccionado (título, autor o ISBN).

## Clase Libro:
Esta clase es una simple clase de datos que representa un libro con un título, autor e ISBN. Se utiliza para almacenar la información de los libros en el programa.

## Clase RegistroPrestamo
Esta clase es un clase simple clase de datos que representa un registro de préstamo sobre un libro, guardando el usuario, el título y la fecha de devolución. Se utiliza para guardar los registros de los libros que se prestan.

## Comentarios en Línea:
Se han proporcionado comentarios en línea a lo largo del código para explicar lo que hace cada sección y cada línea de código. Estos comentarios explican las operaciones realizadas en diferentes partes del programa, como la creación de componentes de la interfaz de usuario, la manipulación de archivos, la carga de datos y la gestión de registros de préstamo.

La documentación proporcionada debería ayudarte a comprender el funcionamiento general del programa y cómo se relacionan las diferentes partes para crear un sistema de gestión de biblioteca.
