package gestionBiblioteca.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gestionBiblioteca.entity.Docente;
import gestionBiblioteca.entity.Estudiante;
import gestionBiblioteca.entity.Persona;
import gestionBiblioteca.utils.UtilsBD;

public class PersonaDaoImpl implements PersonaDao {

	private UtilsBD utilsBD = UtilsBD.getInstance();

	public String insertar(Persona persona) {
		try {
			utilsBD.sentencia(
					"INSERT INTO persona(cedula, nombre, apellido, edad, telefono, ciudad, sexo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
					new Object[] { persona.getCedula(), persona.getNombre(), persona.getApellido(), persona.getEdad(),
							persona.getTelefono(), persona.getCiudad(), persona.getSexo(), persona.getTipo() });
			if (persona.getTipo() == 1) {
				Estudiante estudiante = (Estudiante) persona;
				utilsBD.sentencia("INSERT INTO estudiante(cedula, semestre, carrera) VALUES (?, ?, ?);",
						new Object[] { estudiante.getCedula(), estudiante.getSemestre(), estudiante.getCarrera() });
			} else {
				Docente docente = (Docente) persona;
				utilsBD.sentencia("INSERT INTO docente(cedula, materia) VALUES (?, ?);",
						new Object[] { docente.getCedula(), docente.getMateria() });
			}
		} catch (Exception e) {
			return "FNo se pudo ingresar a la persona, causa: " + e.getMessage();
		}
		return "TSe ingreso correctamente a la persona";
	}

	public List<Persona> obtenerTodos() {
		List<Persona> listaPersona = new ArrayList<Persona>();
		try {
			ResultSet rsEstudiante = utilsBD.consulta(
					"SELECT persona.*, estudiante.semestre, estudiante.carrera FROM persona, estudiante WHERE tipo=1;");
			if (rsEstudiante != null) {
				while (rsEstudiante.next())
					listaPersona.add(new Estudiante(rsEstudiante.getString("cedula"), rsEstudiante.getString("nombre"),
							rsEstudiante.getString("apellido"), rsEstudiante.getString("sexo").charAt(0),
							rsEstudiante.getInt("edad"), rsEstudiante.getString("ciudad"),
							rsEstudiante.getString("telefono"), rsEstudiante.getInt("tipo"),
							rsEstudiante.getString("carrera"), rsEstudiante.getInt("semestre")));
				rsEstudiante.close();
			}
			ResultSet rsDocente = utilsBD
					.consulta("SELECT persona.*, docente.materia FROM persona, docente WHERE tipo=2;");
			if (rsDocente != null) {
				while (rsDocente.next())
					listaPersona.add(new Docente(rsDocente.getString("cedula"), rsDocente.getString("nombre"),
							rsDocente.getString("apellido"), rsDocente.getString("sexo").charAt(0),
							rsDocente.getInt("edad"), rsDocente.getString("ciudad"), rsDocente.getString("telefono"),
							rsDocente.getInt("tipo"), rsDocente.getString("materia")));
				rsDocente.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Persona>();
		}
		return listaPersona;
	}

	public Persona obtenerPorCedula(String cedula) {
		Persona persona = null;
		try {
			ResultSet rsPersona = utilsBD.consulta("SELECT * FROM persona WHERE cedula='" + cedula + "';");
			if (rsPersona != null) {
				while (rsPersona.next()) {
					int tipo = rsPersona.getInt("tipo");
					if (tipo == 1) {
						ResultSet rsEstudiante = utilsBD
								.consulta("SELECT * FROM estudiante WHERE cedula='" + cedula + "';");
						if (rsEstudiante != null) {
							while (rsEstudiante.next())
								persona = new Estudiante(rsPersona.getString("cedula"), rsPersona.getString("nombre"),
										rsPersona.getString("apellido"), rsPersona.getString("sexo").charAt(0),
										rsPersona.getInt("edad"), rsPersona.getString("ciudad"),
										rsPersona.getString("telefono"), rsPersona.getInt("tipo"),
										rsEstudiante.getString("carrera"), rsEstudiante.getInt("semestre"));
							rsEstudiante.close();
						}
					} else if (tipo == 2) {
						ResultSet rsDocente = utilsBD.consulta("SELECT * FROM docente WHERE cedula='" + cedula + "';");
						if (rsDocente != null) {
							while (rsDocente.next())
								persona = new Docente(rsPersona.getString("cedula"), rsPersona.getString("nombre"),
										rsPersona.getString("apellido"), rsPersona.getString("sexo").charAt(0),
										rsPersona.getInt("edad"), rsPersona.getString("ciudad"),
										rsPersona.getString("telefono"), rsPersona.getInt("tipo"),
										rsDocente.getString("materia"));
							rsDocente.close();
						}
					}
				}
				rsPersona.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persona;
	}

	public String modificar(Persona persona) {
		try {
			utilsBD.sentencia(
					"UPDATE persona SET nombre=?, apellido=?, edad=?, telefono=?, ciudad=?, sexo=?, tipo=? WHERE cedula=?;",
					new Object[] { persona.getNombre(), persona.getApellido(), persona.getEdad(), persona.getTelefono(),
							persona.getCiudad(), persona.getSexo(), persona.getTipo(), persona.getCedula() });
			if (persona.getTipo() == 1) {
				Estudiante estudiante = (Estudiante) persona;
				utilsBD.sentencia("UPDATE estudiante SET semestre=?, carrera=? WHERE cedula=?;",
						new Object[] { estudiante.getSemestre(), estudiante.getCarrera(), estudiante.getCedula() });
			} else {
				Docente docente = (Docente) persona;
				utilsBD.sentencia("UPDATE docente SET materia=? WHERE cedula=?;",
						new Object[] { docente.getMateria(), docente.getCedula() });
			}
		} catch (Exception e) {
			return "FNo se pudo modificar a la persona, causa: " + e.getMessage();
		}
		return "TSe modifico correctamente a la persona";
	}

	public String eliminar(Persona persona) {
		try {
			utilsBD.sentencia("DELETE FROM persona WHERE cedula=?;", new Object[] { persona.getCedula() });
		} catch (Exception e) {
			return "FNo se pudo eliminar a la persona, causa: " + e.getMessage();
		}
		return "TSe elimino correctamente a la persona";
	}

}
