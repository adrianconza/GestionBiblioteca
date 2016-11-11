package gestionBiblioteca.entity;

import java.util.Date;
import java.util.List;

public class GestionBiblioteca {

	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Persona persona;
	private List<MaterialBibliografico> listaMaterialBibliografico;

	public GestionBiblioteca() {
	}

}
