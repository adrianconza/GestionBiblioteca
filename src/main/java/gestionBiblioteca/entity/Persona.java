package gestionBiblioteca.entity;

public class Persona {

	private String cedula;
	private String nombre;
	private String apellido;
	private char sexo;
	private int edad;
	private String ciudad;
	private String telefono;
	private int tipo;

	public Persona() {
	}

	public Persona(String cedula) {
		this.cedula = cedula;
	}

	public Persona(String cedula, String nombre, String apellido, char sexo, int edad, String ciudad, String telefono,
			int tipo) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.edad = edad;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.tipo = tipo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo + ", " + cedula + ", " + nombre + ", " + apellido + ", " + sexo + ", " + edad + ", " + ciudad + ", "
				+ telefono;
	}

}
