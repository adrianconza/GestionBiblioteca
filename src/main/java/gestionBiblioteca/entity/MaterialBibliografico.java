package gestionBiblioteca.entity;

public class MaterialBibliografico {

	private String codigo;
	private String titulo;
	private int anioPublicacion;
	private int unidades;
	private int tipo;

	public MaterialBibliografico() {
	}

	public MaterialBibliografico(String codigo, String titulo, int anioPublicacion, int unidades, int tipo) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.anioPublicacion = anioPublicacion;
		this.unidades = unidades;
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo + ", " + codigo + ", " + titulo + ", " + anioPublicacion + ", " + unidades;
	}

}
