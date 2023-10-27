import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GestorBiblioteca extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField agregarTitulo;
    private JTextField agregarAutor;
    private JTextField agregarISBN;
    private JTextField textField_3;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnAutor;
    private JRadioButton rdbtnIsbn;
    private JButton btn;
    private JButton btnVerRegistros;
    private DefaultTableModel registrosTableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestorBiblioteca frame = new GestorBiblioteca();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GestorBiblioteca() {
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 901, 604);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 122, 558, 432);
        contentPane.add(scrollPane);

        // Crea un modelo de tabla con las columnas "Título", "Autor" e "ISBN"
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Título");
        tableModel.addColumn("Autor");
        tableModel.addColumn("ISBN");

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        agregarTitulo = new JTextField();
        agregarTitulo.setBounds(654, 230, 203, 20);
        contentPane.add(agregarTitulo);
        agregarTitulo.setColumns(10);

        agregarAutor = new JTextField();
        agregarAutor.setColumns(10);
        agregarAutor.setBounds(654, 284, 203, 20);
        contentPane.add(agregarAutor);

        agregarISBN = new JTextField();
        agregarISBN.setColumns(10);
        agregarISBN.setBounds(654, 333, 203, 20);
        contentPane.add(agregarISBN);

        JLabel lblNewLabel = new JLabel("Título");
        lblNewLabel.setBounds(598, 233, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblAutor = new JLabel("Autor");
        lblAutor.setBounds(598, 287, 46, 14);
        contentPane.add(lblAutor);

        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setBounds(603, 336, 46, 14);
        contentPane.add(lblIsbn);

        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.setBounds(768, 377, 89, 23);
        contentPane.add(btnNewButton);

        // Agregar ActionListener al botón "Agregar"
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = agregarTitulo.getText();
                String autor = agregarAutor.getText();
                long isbn = Long.parseLong(agregarISBN.getText());

                // Crea un objeto de la clase "Libro" con los datos proporcionados
                Libro libro = new Libro(titulo, autor, isbn);

                // Escribir el libro en el archivo "datos.txt"
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos.txt", true))) {
                    writer.write(libro.getTitulo() + "," + libro.getAutor() + "," + libro.getISBN());
                    writer.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Limpiar los campos de entrada después de agregar el libro
                agregarTitulo.setText("");
                agregarAutor.setText("");
                agregarISBN.setText("");

                // Agregar el libro a la tabla
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{libro.getTitulo(), libro.getAutor(), libro.getISBN()});
            }
        });

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(10, 91, 203, 20);
        contentPane.add(textField_3);

        rdbtnNewRadioButton = new JRadioButton("Título");
        rdbtnNewRadioButton.setBounds(219, 90, 64, 23);
        contentPane.add(rdbtnNewRadioButton);

        rdbtnAutor = new JRadioButton("Autor");
        rdbtnAutor.setBounds(296, 90, 64, 23);
        contentPane.add(rdbtnAutor);

        rdbtnIsbn = new JRadioButton("ISBN");
        rdbtnIsbn.setBounds(362, 90, 64, 23);
        contentPane.add(rdbtnIsbn);

        JButton btnNewButton_1 = new JButton("Buscar");
        btnNewButton_1.setBounds(442, 88, 89, 23);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel_1 = new JLabel("Biblioteca");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        lblNewLabel_1.setBounds(357, 23, 174, 34);
        contentPane.add(lblNewLabel_1);

        // Botón para ver registros
        btnVerRegistros = new JButton("Ver Registros");
        btnVerRegistros.setBounds(673, 504, 124, 23);
        contentPane.add(btnVerRegistros);

        // Crear un modelo de tabla para los registros de préstamo
        registrosTableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };

        registrosTableModel.addColumn("Usuario");
        registrosTableModel.addColumn("Título");
        registrosTableModel.addColumn("Fecha de Devolución");

        // Agregar una columna con checkboxes para seleccionar registros
        registrosTableModel.addColumn("Seleccionar");

        btnVerRegistros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaRegistros();
            }
        });

        // Cargar datos desde el archivo "datos.txt" al iniciar la aplicación
        cargarDatosDesdeArchivo();

        // Agregar ActionListener al botón "Buscar"
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = textField_3.getText();
                List<Libro> librosEncontrados = buscarLibros(searchTerm);

                // Crear una nueva ventana emergente para mostrar los resultados de la búsqueda
                JFrame resultadoFrame = new JFrame("Resultados de la Búsqueda");
                resultadoFrame.setBounds(100, 100, 600, 400);
                JPanel resultadoPanel = new JPanel();
                resultadoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                resultadoFrame.setContentPane(resultadoPanel);
                resultadoPanel.setLayout(null);

                // Crear un modelo de tabla para mostrar los resultados
                DefaultTableModel resultadoTableModel = new DefaultTableModel();
                resultadoTableModel.addColumn("Título");
                resultadoTableModel.addColumn("Autor");
                resultadoTableModel.addColumn("ISBN");

                JTable resultadoTable = new JTable(resultadoTableModel);
                JScrollPane resultadoScrollPane = new JScrollPane(resultadoTable);
                resultadoScrollPane.setBounds(10, 50, 558, 300);
                resultadoPanel.add(resultadoScrollPane);

                // Agregar los libros encontrados a la tabla de resultados
                for (Libro libro : librosEncontrados) {
                    resultadoTableModel.addRow(new Object[]{libro.getTitulo(), libro.getAutor(), libro.getISBN()});
                }

                resultadoFrame.setVisible(true);
            }
        });
    }

    private void mostrarVentanaRegistros() {
        // Crear una nueva ventana para los registros de préstamo
        JFrame ventanaRegistros = new JFrame("Registros de Préstamo");
        ventanaRegistros.setBounds(100, 100, 800, 400);
        JPanel panelRegistros = new JPanel();
        panelRegistros.setBorder(new EmptyBorder(5, 5, 5, 5));
        ventanaRegistros.setContentPane(panelRegistros);
        panelRegistros.setLayout(null);

        // Crear una tabla para los registros de préstamo
        JTable tablaRegistros = new JTable(registrosTableModel);
        JScrollPane scrollPaneRegistros = new JScrollPane(tablaRegistros);
        scrollPaneRegistros.setBounds(10, 50, 558, 300);
        panelRegistros.add(scrollPaneRegistros);

        // Crear JTextField para ingresar información del nuevo préstamo
        JTextField usuarioTextField = new JTextField();
        JTextField tituloTextField = new JTextField();
        JTextField fechaTextField = new JTextField();
        
        usuarioTextField.setBounds(578, 50, 200, 20);
        tituloTextField.setBounds(578, 80, 200, 20);
        fechaTextField.setBounds(578, 110, 200, 20);
        
        panelRegistros.add(usuarioTextField);
        panelRegistros.add(tituloTextField);
        panelRegistros.add(fechaTextField);

        // Etiquetas para los JTextField
        JLabel lblUsuario = new JLabel("Usuario");
        JLabel lblTitulo = new JLabel("Título");
        JLabel lblFecha = new JLabel("Fecha de Devolución");
        
        lblUsuario.setBounds(500, 50, 68, 14);
        lblTitulo.setBounds(500, 80, 68, 14);
        lblFecha.setBounds(500, 110, 100, 14);
        
        panelRegistros.add(lblUsuario);
        panelRegistros.add(lblTitulo);
        panelRegistros.add(lblFecha);
        
        // Botón para agregar registros
        JButton btnAgregarRegistro = new JButton("Agregar Registro");
        btnAgregarRegistro.setBounds(578, 140, 150, 30);
        panelRegistros.add(btnAgregarRegistro);

        btnAgregarRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioTextField.getText();
                String titulo = tituloTextField.getText();
                String fechaStr = fechaTextField.getText();

                try {
                    LocalDate fechaDevolucion = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    
                    // Agregar el nuevo registro a la tabla de registros
                    registrosTableModel.addRow(new Object[] { usuario, titulo, fechaDevolucion, false });
                    
                    // Guardar el registro en el archivo "prestamos.txt"
                    guardarRegistrosEnArchivo();
                    
                    // Limpiar los campos de entrada
                    usuarioTextField.setText("");
                    tituloTextField.setText("");
                    fechaTextField.setText("");
                } catch (DateTimeParseException ex) {
                    // Manejar errores de formato de fecha
                    JOptionPane.showMessageDialog(ventanaRegistros, "Formato de fecha incorrecto. Utiliza el formato 'yyyy-MM-dd'.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Resto del código para cargar registros, eliminarlos, etc.
        // Botón para eliminar registros seleccionados
        JButton btnEliminarRegistros = new JButton("Eliminar Registros Seleccionados");
        btnEliminarRegistros.setBounds(10, 10, 250, 30);
        panelRegistros.add(btnEliminarRegistros);

        btnEliminarRegistros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarRegistrosSeleccionados();
            }
        });

        // Cargar los registros de préstamo desde el archivo "prestamos.txt"
        cargarRegistrosPrestamoDesdeArchivo();

        
        // Mostrar la ventana de registros
        ventanaRegistros.setVisible(true);
    }


    private void eliminarRegistrosSeleccionados() {
        int rowCount = registrosTableModel.getRowCount();
        List<Integer> filasAEliminar = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            boolean seleccionado = (Boolean) registrosTableModel.getValueAt(i, 3);
            if (seleccionado) {
                filasAEliminar.add(i);
            }
        }

        // Eliminar registros seleccionados
        for (int i = filasAEliminar.size() - 1; i >= 0; i--) {
            int fila = filasAEliminar.get(i);
            registrosTableModel.removeRow(fila);
        }

        // Guardar los registros restantes en el archivo "prestamos.txt"
        guardarRegistrosEnArchivo();
    }

    private void guardarRegistrosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prestamos.txt"))) {
            for (int i = 0; i < registrosTableModel.getRowCount(); i++) {
                String usuario = registrosTableModel.getValueAt(i, 0).toString();
                String titulo = registrosTableModel.getValueAt(i, 1).toString();
                LocalDate fechaDevolucion = (LocalDate) registrosTableModel.getValueAt(i, 2);
                String fechaDevolucionStr = fechaDevolucion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                writer.write(usuario + "," + titulo + "," + fechaDevolucionStr);
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método para cargar registros de préstamo desde el archivo "prestamos.txt"
    private void cargarRegistrosPrestamoDesdeArchivo() {
        registrosTableModel.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String usuario = parts[0];
                    String titulo = parts[1];
                    LocalDate fechaDevolucion = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    registrosTableModel.addRow(new Object[]{usuario, titulo, fechaDevolucion, false});
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método para cargar datos desde el archivo "datos.txt"
    private void cargarDatosDesdeArchivo() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    long isbn = Long.parseLong(parts[2]);
                    model.addRow(new Object[]{titulo, autor, isbn});
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método para buscar libros en base al término de búsqueda
    private List<Libro> buscarLibros(String searchTerm) {
        List<Libro> librosEncontrados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    long isbn = Long.parseLong(parts[2]);

                    if ((rdbtnNewRadioButton.isSelected() && titulo.equalsIgnoreCase(searchTerm))
                            || (rdbtnAutor.isSelected() && autor.equalsIgnoreCase(searchTerm))
                            || (rdbtnIsbn.isSelected() && isbn == Long.parseLong(searchTerm))) {
                        librosEncontrados.add(new Libro(titulo, autor, isbn));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return librosEncontrados;
    }
}

