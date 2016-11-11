package gestionBiblioteca.service;

import java.util.List;

import gestionBiblioteca.dao.GestionBibliotecaDao;
import gestionBiblioteca.dao.GestionBibliotecaDaoImpl;
import gestionBiblioteca.entity.GestionBiblioteca;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Persona;

public class GestionBibliotecaServiceImpl implements GestionBibliotecaService {

	private GestionBibliotecaDao gestionBibliotecaDao = new GestionBibliotecaDaoImpl();
	private PersonaService personaService = new PersonaServiceImpl();
	private MaterialBibliograficoService materialBibliograficoService = new MaterialBibliograficoServiceImpl();

	public String insertar(GestionBiblioteca gestionBiblioteca) {
		String mensaje = "";
		if ((mensaje = validarGestionBiblioteca(gestionBiblioteca)) != null)
			return mensaje;
		else if (gestionBiblioteca.getFechaDevolucion() != null)
			return "FNo puede ingresar la fecha de devolucion al crear el registro";
		else
			return gestionBibliotecaDao.insertar(gestionBiblioteca);
	}

	public List<GestionBiblioteca> obtenerTodos() {
		return gestionBibliotecaDao.obtenerTodos();
	}

	public GestionBiblioteca obtenerPorId(int id) {
		return gestionBibliotecaDao.obtenerPorId(id);
	}

	public String modificar(GestionBiblioteca gestionBiblioteca) {
		String mensaje = "";
		if ((mensaje = validarGestionBiblioteca(gestionBiblioteca)) != null)
			return mensaje;
		else
			return gestionBibliotecaDao.modificar(gestionBiblioteca);
	}

	public String eliminar(GestionBiblioteca gestionBiblioteca) {
		if (gestionBiblioteca == null)
			return "FEscoja una gestion biblioteca para eliminar";
		else
			return gestionBibliotecaDao.eliminar(gestionBiblioteca);
	}

	private String validarGestionBiblioteca(GestionBiblioteca gestionBiblioteca) {
		if (gestionBiblioteca == null)
			return "FEscoja una gestion biblioteca para insertar";
		else if (gestionBiblioteca.getId() == 0)
			return "FIngrese el id de la gestion biblioteca";
		else if (gestionBiblioteca.getFechaPrestamo() == null)
			return "FIngrese la fecha de prestamo de la gestion biblioteca";
		else if (gestionBiblioteca.getPersona() == null
				|| gestionBiblioteca.getPersona().getCedula().compareToIgnoreCase("") == 0)
			return "FIngrese la persona";
		else if (gestionBiblioteca.getListaMaterialBibliografico() == null
				|| gestionBiblioteca.getListaMaterialBibliografico().isEmpty())
			return "FIngrese el material bibliografico";
		else {
			Persona persona = personaService.obtenerPorCedula(gestionBiblioteca.getPersona().getCedula());
			if (persona == null)
				return "FLa persona que esta ingresando no exite";
			else
				gestionBiblioteca.setPersona(persona);
			for (int i = 0; i < gestionBiblioteca.getListaMaterialBibliografico().size(); i++) {
				String codigo = gestionBiblioteca.getListaMaterialBibliografico().get(i).getCodigo();
				MaterialBibliografico mb = materialBibliograficoService.obtenerPorCodigo(codigo);
				if (mb == null)
					return "FEl material bibliografico con codigo: " + codigo + " no exite";
				else
					gestionBiblioteca.getListaMaterialBibliografico().set(i, mb);
			}
		}
		return null;
	}

}
