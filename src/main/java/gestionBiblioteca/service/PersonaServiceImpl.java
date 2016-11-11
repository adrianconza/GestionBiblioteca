package gestionBiblioteca.service;

import java.util.List;

import gestionBiblioteca.dao.PersonaDao;
import gestionBiblioteca.dao.PersonaDaoImpl;
import gestionBiblioteca.entity.Docente;
import gestionBiblioteca.entity.Estudiante;
import gestionBiblioteca.entity.Persona;

public class PersonaServiceImpl implements PersonaService {

	private PersonaDao personaDao = new PersonaDaoImpl();

	public String insertar(Persona persona) {
		String mensaje = "";
		if ((mensaje = validarPersona(persona)) != null)
			return mensaje;
		else if (obtenerPorCedula(persona.getCedula()) != null)
			return "FLa persona que va a ingresar ya existe";
		else {
			if (persona.getTipo() == 1) {
				Estudiante estudiante = (Estudiante) persona;
				estudiante.setCarrera(estudiante.getCarrera().toUpperCase());
				persona = estudiante;
			} else {
				Docente docente = (Docente) persona;
				docente.setMateria(docente.getMateria().toUpperCase());
				persona = docente;
			}
			persona.setCedula(persona.getCedula().toUpperCase());
			persona.setNombre(persona.getNombre().toUpperCase());
			persona.setApellido(persona.getApellido().toUpperCase());
			persona.setCiudad(persona.getCiudad().toUpperCase());
			return personaDao.insertar(persona);
		}
	}

	public List<Persona> obtenerTodos() {
		return personaDao.obtenerTodos();
	}

	public Persona obtenerPorCedula(String cedula) {
		return personaDao.obtenerPorCedula(cedula);
	}

	public String modificar(Persona persona) {
		String mensaje = "";
		if ((mensaje = validarPersona(persona)) != null)
			return mensaje;
		else {
			if (persona.getTipo() == 1) {
				Estudiante estudiante = (Estudiante) persona;
				estudiante.setCarrera(estudiante.getCarrera().toUpperCase());
				persona = estudiante;
			} else {
				Docente docente = (Docente) persona;
				docente.setMateria(docente.getMateria().toUpperCase());
				persona = docente;
			}
			persona.setCedula(persona.getCedula().toUpperCase());
			persona.setNombre(persona.getNombre().toUpperCase());
			persona.setApellido(persona.getApellido().toUpperCase());
			persona.setCiudad(persona.getCiudad().toUpperCase());
			return personaDao.modificar(persona);
		}
	}

	public String eliminar(Persona persona) {
		if (persona == null)
			return "FEscoja una persona para eliminar";
		else
			return personaDao.eliminar(persona);
	}

	private String validarPersona(Persona persona) {
		if (persona == null)
			return "FEscoja una persona para insertar";
		else if (persona.getCedula() == null || persona.getCedula().compareToIgnoreCase("") == 0)
			return "FIngrese la cedula de la persona";
		else if (persona.getNombre() == null || persona.getNombre().compareToIgnoreCase("") == 0)
			return "FIngrese el nombre de la persona";
		else if (persona.getApellido() == null || persona.getApellido().compareToIgnoreCase("") == 0)
			return "FIngrese el apellido de la persona";
		else if (String.valueOf(persona.getSexo()) == null
				|| String.valueOf(persona.getSexo()).compareToIgnoreCase("") == 0)
			return "FIngrese el sexo de la persona";
		else if (persona.getEdad() == 0)
			return "FIngrese la edad de la persona";
		else if (persona.getCiudad() == null || persona.getCiudad().compareToIgnoreCase("") == 0)
			return "FIngrese la ciudad de la persona";
		else if (persona.getTelefono() == null || persona.getTelefono().compareToIgnoreCase("") == 0)
			return "FIngrese el telefono de la persona";
		else if (!isDigit(persona.getTelefono()))
			return "FEl telefono solo puede ser digitos";
		else if (persona.getTipo() == 0)
			return "FIngrese el tipo de la persona";
		else {
			if (persona.getTipo() == 1) {
				Estudiante estudiante = (Estudiante) persona;
				if (estudiante.getCarrera() == null || estudiante.getCarrera().compareToIgnoreCase("") == 0)
					return "FIngrese la carrera para el estudiante";
				else if (estudiante.getSemestre() == 0)
					return "FIngrese el semestre para el estudiante";
			} else {
				Docente docente = (Docente) persona;
				if (docente.getMateria() == null || docente.getMateria().compareToIgnoreCase("") == 0)
					return "FIngrese la materia del docente";
			}
		}
		return null;
	}

	private boolean isDigit(String texto) {
		for (int i = 0; i < texto.length(); i++)
			if (!Character.isDigit(texto.charAt(i)))
				return false;
		return true;
	}

}
