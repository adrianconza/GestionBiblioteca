package gestionBiblioteca.entity;

import java.util.Date;
import java.util.List;

import gestionBiblioteca.utils.UtilsDate;

public class GestionBiblioteca {

	private int id;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Persona persona;
	private List<MaterialBibliografico> listaMaterialBibliografico;

	public GestionBiblioteca() {
	}

	public GestionBiblioteca(int id, Date fechaPrestamo, Date fechaDevolucion, Persona persona,
			List<MaterialBibliografico> listaMaterialBibliografico) {
		this.id = id;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.persona = persona;
		this.listaMaterialBibliografico = listaMaterialBibliografico;
	}

	public GestionBiblioteca(String datoAux[], Persona persona,
			List<MaterialBibliografico> listaMaterialBibliografico) {
		this(Integer.parseInt(datoAux[1]), UtilsDate.stringToDate(datoAux[2]), UtilsDate.stringToDate(datoAux[3]),
				persona, listaMaterialBibliografico);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<MaterialBibliografico> getListaMaterialBibliografico() {
		return listaMaterialBibliografico;
	}

	public void setListaMaterialBibliografico(List<MaterialBibliografico> listaMaterialBibliografico) {
		this.listaMaterialBibliografico = listaMaterialBibliografico;
	}

	@Override
	public String toString() {
		String materialBibliografico = "";
		for (MaterialBibliografico mb : listaMaterialBibliografico)
			materialBibliografico += mb.getCodigo() + ",";
		return id + "," + UtilsDate.dateToString(fechaPrestamo) + "," + UtilsDate.dateToString(fechaDevolucion) + ","
				+ persona.getCedula() + "," + materialBibliografico.substring(0, materialBibliografico.length() - 1);
	}

}
