package gestionBiblioteca.entity;

public class Tesis extends MaterialBibliografico {

	private String autor;
	private String tutor;

	public Tesis() {
		super();
	}

	public Tesis(String codigo, String titulo, int anioPublicacion, int unidades, int tipo) {
		super(codigo, titulo, anioPublicacion, unidades, tipo);
	}

	public Tesis(String codigo, String titulo, int anioPublicacion, int unidades, int tipo, String autor,
			String tutor) {
		this(codigo, titulo, anioPublicacion, unidades, tipo);
		this.autor = autor;
		this.tutor = tutor;
	}

	public Tesis(String datoAux[]) {
		this(datoAux[1], datoAux[2], Integer.parseInt(datoAux[3]), Integer.parseInt(datoAux[4]),
				Integer.parseInt(datoAux[0]), datoAux[5], datoAux[6]);
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	@Override
	public String toString() {
		return super.toString() + "," + autor + "," + tutor;
	}

}
