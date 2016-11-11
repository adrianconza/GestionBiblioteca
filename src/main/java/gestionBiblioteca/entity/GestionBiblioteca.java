package gestionBiblioteca.entity;

import java.util.Date;

public class GestionBiblioteca {

	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Persona persona;
	private MaterialBibliografico[] materialBibliografico;

	public GestionBiblioteca() {
	}

	public GestionBiblioteca(Date fechaPrestamo, Date fechaDevolucion, Persona persona,
			MaterialBibliografico[] materialBibliografico) {
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.persona = persona;
		this.materialBibliografico = materialBibliografico;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public MaterialBibliografico[] getMaterialBibliografico() {
		return materialBibliografico;
	}

	public void setMaterialBibliografico(MaterialBibliografico[] materialBibliografico) {
		this.materialBibliografico = materialBibliografico;
	}

}
