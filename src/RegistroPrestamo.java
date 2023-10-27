import java.time.LocalDate;

public class RegistroPrestamo {
    private String usuario;
    private String titulo;
    private LocalDate fechaDevolucion;

    public RegistroPrestamo(String usuario, String titulo, LocalDate fechaDevolucion) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}
