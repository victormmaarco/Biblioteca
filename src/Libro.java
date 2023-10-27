
public class Libro {
	private String titulo;
	private String autor;
	private long ISBN;

	public Libro(String titulo, String autor, long ISBN) {
		this.titulo = titulo;
		this.autor = autor;
		this.ISBN = ISBN;
	}

	public String getAutor() {
		// TODO Auto-generated method stub
		return autor;
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return titulo;
	}

	public long getISBN() {
		// TODO Auto-generated method stub
		return ISBN;
	}
}