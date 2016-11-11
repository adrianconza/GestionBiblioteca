package gestionBiblioteca.entity;

public class Libro extends MaterialBibliografico {

	private String editorial;
	private String autor;
	private int edicion;

	public Libro() {
		super();
	}

	public Libro(String codigo, String titulo, int anioPublicacion, int unidades, int tipo) {
		super(codigo, titulo, anioPublicacion, unidades, tipo);
	}

	public Libro(String codigo, String titulo, int anioPublicacion, int unidades, int tipo, String editorial,
			String autor, int edicion) {
		this(codigo, titulo, anioPublicacion, unidades, tipo);
		this.editorial = editorial;
		this.autor = autor;
		this.edicion = edicion;
	}

	public Libro(String datoAux[]) {
		this(datoAux[1], datoAux[2], Integer.parseInt(datoAux[3]), Integer.parseInt(datoAux[4]),
				Integer.parseInt(datoAux[0]), datoAux[5], datoAux[6], Integer.parseInt(datoAux[7]));
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	@Override
	public String toString() {
		return super.toString() + "," + editorial + "," + autor + "," + edicion;
	}

}
