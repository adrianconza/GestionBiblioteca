package gestionBiblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import gestionBiblioteca.entity.GestionBiblioteca;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Persona;
import gestionBiblioteca.utils.UtilsArchivos;

public class GestionBibliotecaDaoImpl implements GestionBibliotecaDao {

	private String bdGestionBiblioteca = UtilsArchivos.getBDGestionBiblioteca();
	private PersonaDao personaDao = new PersonaDaoImpl();
	private MaterialBibliograficoDao materialBibliograficoDao = new MaterialBibliograficoDaoImpl();

	public String insertar(GestionBiblioteca gestionBiblioteca) {
		try {
			UtilsArchivos.insertar(bdGestionBiblioteca, gestionBiblioteca.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return "TNo se pudo ingresar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe ingreso correctamente la gestion biblioteca";
	}

	public List<GestionBiblioteca> obtenerTodos() {
		List<GestionBiblioteca> listaGestionBiblioteca = new ArrayList<GestionBiblioteca>();
		try {
			for (String dato : UtilsArchivos.obtenerTodos(bdGestionBiblioteca)) {
				String datoAux[] = dato.split(",");
				Persona persona = personaDao.obtenerPorCedula(datoAux[3]);
				List<MaterialBibliografico> listaMaterialBibliografico = new ArrayList<MaterialBibliografico>();
				for (int i = 4; i < datoAux.length; i++)
					listaMaterialBibliografico.add(materialBibliograficoDao.obtenerPorCodigo(datoAux[i]));
				listaGestionBiblioteca.add(new GestionBiblioteca(datoAux, persona, listaMaterialBibliografico));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaGestionBiblioteca;
	}

	public GestionBiblioteca obtenerPorId(int id) {
		for (GestionBiblioteca gestionBiblioteca : obtenerTodos())
			if (gestionBiblioteca.getId() == id)
				return gestionBiblioteca;
		return null;
	}

	public String modificar(GestionBiblioteca gestionBiblioteca) {
		try {
			List<GestionBiblioteca> listaGestionBiblioteca = obtenerTodos();
			for (int i = 0; i < listaGestionBiblioteca.size(); i++)
				if (gestionBiblioteca.getId() == listaGestionBiblioteca.get(i).getId()) {
					listaGestionBiblioteca.set(i, gestionBiblioteca);
					break;
				}
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
			for (int i = 0; i < listaGestionBiblioteca.size(); i++)
				if (gestionBiblioteca.getId() == listaGestionBiblioteca.get(i).getId()) {
					listaGestionBiblioteca.remove(i);
					break;
				}
			UtilsArchivos.modificarEliminar(bdGestionBiblioteca,
					UtilsArchivos.generarListaGuardar(listaGestionBiblioteca));
		} catch (Exception e) {
			return "TNo se pudo eliminar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe elimino correctamente la gestion biblioteca";
	}

}
