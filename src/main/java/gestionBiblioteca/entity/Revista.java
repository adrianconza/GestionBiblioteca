package gestionBiblioteca.entity;

import java.util.Date;

import gestionBiblioteca.utils.UtilsDate;

public class Revista extends MaterialBibliografico {

	private Date fechaPublicacion;
	private String tipoRevista;

	public Revista() {
		super();
	}

	public Revista(String codigo, String titulo, int anioPublicacion, int unidades, int tipo) {
		super(codigo, titulo, anioPublicacion, unidades, tipo);
	}

	public Revista(String codigo, String titulo, int anioPublicacion, int unidades, int tipo, Date fechaPublicacion,
			String tipoRevista) {
		this(codigo, titulo, anioPublicacion, unidades, tipo);
		this.fechaPublicacion = fechaPublicacion;
		this.tipoRevista = tipoRevista;
	}

	public Revista(String datoAux[]) {
		this(datoAux[1], datoAux[2], Integer.parseInt(datoAux[3]), Integer.parseInt(datoAux[4]),
				Integer.parseInt(datoAux[0]), UtilsDate.stringToDate(datoAux[5]), datoAux[6]);
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getTipoRevista() {
		return tipoRevista;
	}

	public void setTipoRevista(String tipoRevista) {
		this.tipoRevista = tipoRevista;
	}

	@Override
	public String toString() {
		return super.toString() + "," + UtilsDate.dateToString(fechaPublicacion) + "," + tipoRevista;
	}

}
