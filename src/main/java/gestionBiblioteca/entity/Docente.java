package gestionBiblioteca.entity;

public class Docente extends Persona {

	private String materia;

	public Docente() {
		super();
	}

	public Docente(String cedula, String nombre, String apellido, char sexo, int edad, String ciudad, String telefono,
			int tipo) {
		super(cedula, nombre, apellido, sexo, edad, ciudad, telefono, tipo);
	}

	public Docente(String cedula, String nombre, String apellido, char sexo, int edad, String ciudad, String telefono,
			int tipo, String materia) {
		this(cedula, nombre, apellido, sexo, edad, ciudad, telefono, tipo);
		this.materia = materia;
	}

	public Docente(String datoAux[]) {
		this(datoAux[1], datoAux[2], datoAux[3], datoAux[4].charAt(0), Integer.parseInt(datoAux[5]), datoAux[6],
				datoAux[7], Integer.parseInt(datoAux[0]), datoAux[8]);
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return super.toString() + "," + materia;
	}

}
