package gestionBiblioteca.entity;

public class Estudiante extends Persona {

	private String carrera;
	private int semestre;

	public Estudiante() {
		super();
	}

	public Estudiante(String cedula, String nombre, String apellido, char sexo, int edad, String ciudad,
			String telefono, int tipo) {
		super(cedula, nombre, apellido, sexo, edad, ciudad, telefono, tipo);
	}

	public Estudiante(String cedula, String nombre, String apellido, char sexo, int edad, String ciudad,
			String telefono, int tipo, String carrera, int semestre) {
		this(cedula, nombre, apellido, sexo, edad, ciudad, telefono, tipo);
		this.carrera = carrera;
		this.semestre = semestre;
	}

	public Estudiante(String datoAux[]) {
		this(datoAux[1], datoAux[2], datoAux[3], datoAux[4].charAt(0), Integer.parseInt(datoAux[5]), datoAux[6],
				datoAux[7], Integer.parseInt(datoAux[0]), datoAux[8], Integer.parseInt(datoAux[9]));
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + carrera + ", " + semestre;
	}

}
