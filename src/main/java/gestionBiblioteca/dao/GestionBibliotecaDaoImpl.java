package gestionBiblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import gestionBiblioteca.entity.GestionBiblioteca;
import gestionBiblioteca.utils.UtilsArchivos;

public class GestionBibliotecaDaoImpl implements GestionBibliotecaDao {

	private String bdGestionBiblioteca = UtilsArchivos.getBDGestionBiblioteca();

	public String insertar(GestionBiblioteca gestionBiblioteca) {
		try {
			UtilsArchivos.insertar(bdGestionBiblioteca, gestionBiblioteca.toString());
		} catch (Exception e) {
			return "TNo se pudo ingresar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe ingreso correctamente la gestion biblioteca";
	}

	public List<GestionBiblioteca> obtenerTodos() {
		List<GestionBiblioteca> listaGestionBiblioteca = new ArrayList<GestionBiblioteca>();
		try {
			for (String dato : UtilsArchivos.obtenerTodos(bdGestionBiblioteca))
				listaGestionBiblioteca.add(new GestionBiblioteca(dato.split(",")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaGestionBiblioteca;
	}

	public GestionBiblioteca obtenerPorCedula(String cedula) {
		for (GestionBiblioteca gestionBiblioteca : obtenerTodos())
			if (gestionBiblioteca.getCedula().compareToIgnoreCase(cedula) == 0)
				return gestionBiblioteca;
		return null;
	}

	public String modificar(GestionBiblioteca gestionBiblioteca) {
		try {
			List<GestionBiblioteca> listaGestionBiblioteca = obtenerTodos();
			listaGestionBiblioteca.set(listaGestionBiblioteca.indexOf(gestionBiblioteca), gestionBiblioteca);
			UtilsArchivos.modificarEliminar(bdGestionBiblioteca,
					UtilsArchivos.generarListaGuardar(listaGestionBiblioteca));
		} catch (Exception e) {
			return "TNo se pudo modificar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe modifico correctamente la gestion biblioteca";
	}

	public String eliminar(GestionBiblioteca gestionBiblioteca) {
		try {
			List<GestionBiblioteca> listaGestionBiblioteca = obtenerTodos();
			listaGestionBiblioteca.remove(gestionBiblioteca);
			UtilsArchivos.modificarEliminar(bdGestionBiblioteca,
					UtilsArchivos.generarListaGuardar(listaGestionBiblioteca));
		} catch (Exception e) {
			return "TNo se pudo eliminar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe elimino correctamente la gestion biblioteca";
	}

}
