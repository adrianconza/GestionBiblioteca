package gestionBiblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import gestionBiblioteca.entity.Docente;
import gestionBiblioteca.entity.Estudiante;
import gestionBiblioteca.entity.Persona;
import gestionBiblioteca.utils.UtilsArchivos;

public class PersonaDaoImpl implements PersonaDao {

	private String bdPersona = UtilsArchivos.getBDPersona();

	public String insertar(Persona persona) {
		try {
			if (persona.getTipo() == 1)
				UtilsArchivos.insertar(bdPersona, ((Estudiante) persona).toString());
			else
				UtilsArchivos.insertar(bdPersona, ((Docente) persona).toString());
		} catch (Exception e) {
			return "FNo se pudo ingresar a la persona, causa: " + e.getCause().getMessage();
		}
		return "TSe ingreso correctamente a la persona";
	}

	public List<Persona> obtenerTodos() {
		List<Persona> listaPersona = new ArrayList<Persona>();
		try {
			for (String dato : UtilsArchivos.obtenerTodos(bdPersona)) {
				String datoAux[] = dato.split(",");
				int tipo = Integer.parseInt(datoAux[0]);
				if (tipo == 1)
					listaPersona.add(new Estudiante(datoAux));
				else
					listaPersona.add(new Docente(datoAux));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Persona>();
		}
		return listaPersona;
	}

	public Persona obtenerPorCedula(String cedula) {
		for (Persona persona : obtenerTodos())
			if (persona.getCedula().compareToIgnoreCase(cedula) == 0)
				return persona;
		return null;
	}

	public String modificar(Persona persona) {
		try {
			List<Persona> listaPersona = obtenerTodos();
			for (int i = 0; i < listaPersona.size(); i++)
				if (persona.getCedula().compareToIgnoreCase(listaPersona.get(i).getCedula()) == 0) {
					listaPersona.set(i, persona);
					break;
				}
			UtilsArchivos.modificarEliminar(bdPersona, UtilsArchivos.generarListaGuardar(listaPersona));
		} catch (Exception e) {
			e.printStackTrace();
			return "FNo se pudo modificar a la persona, causa: " + e.getCause();
		}
		return "TSe modifico correctamente a la persona";
	}

	public String eliminar(Persona persona) {
		try {
			List<Persona> listaPersona = obtenerTodos();
			for (int i = 0; i < listaPersona.size(); i++)
				if (persona.getCedula().compareToIgnoreCase(listaPersona.get(i).getCedula()) == 0) {
					listaPersona.remove(i);
					break;
				}
			listaPersona.remove(obtenerPorCedula(persona.getCedula()));
			UtilsArchivos.modificarEliminar(bdPersona, UtilsArchivos.generarListaGuardar(listaPersona));
		} catch (Exception e) {
			return "FNo se pudo eliminar a la persona, causa: " + e.getCause().getMessage();
		}
		return "TSe elimino correctamente a la persona";
	}

}
