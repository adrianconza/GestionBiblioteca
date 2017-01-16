package gestionBiblioteca.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gestionBiblioteca.entity.GestionBiblioteca;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Persona;
import gestionBiblioteca.utils.UtilsBD;

public class GestionBibliotecaDaoImpl implements GestionBibliotecaDao {

	private UtilsBD utilsBD = UtilsBD.getInstance();
	private PersonaDao personaDao = new PersonaDaoImpl();
	private MaterialBibliograficoDao materialBibliograficoDao = new MaterialBibliograficoDaoImpl();

	public String insertar(GestionBiblioteca gestionBiblioteca) {
		try {
			utilsBD.sentencia(
					"INSERT INTO gestion_biblioteca(id, fecha_prestamo, fecha_devolucion, cedula) VALUES (?, ?, ?, ?);",
					new Object[] { gestionBiblioteca.getId(), gestionBiblioteca.getFechaPrestamo(), null,
							gestionBiblioteca.getPersona().getCedula() });
			if (gestionBiblioteca.getListaMaterialBibliografico() != null)
				for (MaterialBibliografico mb : gestionBiblioteca.getListaMaterialBibliografico()) {
					utilsBD.sentencia(
							"INSERT INTO gestion_biblioteca_material_bilbiografico(id, codigo) VALUES (?, ?);",
							new Object[] { gestionBiblioteca.getId(), mb.getCodigo() });
				}
		} catch (Exception e) {
			e.printStackTrace();
			return "TNo se pudo ingresar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe ingreso correctamente la gestion biblioteca";
	}

	public List<GestionBiblioteca> obtenerTodos() {
		List<GestionBiblioteca> listaGestionBiblioteca = new ArrayList<GestionBiblioteca>();
		try {
			ResultSet rsGestionBiblioteca = utilsBD
					.consulta("SELECT id, fecha_prestamo, fecha_devolucion, cedula FROM gestion_biblioteca;");
			if (rsGestionBiblioteca != null) {
				while (rsGestionBiblioteca.next()) {
					Persona persona = personaDao.obtenerPorCedula(rsGestionBiblioteca.getString("cedula"));
					List<MaterialBibliografico> listaMaterialBibliografico = new ArrayList<MaterialBibliografico>();
					ResultSet rsListaMaterialBibliografico = utilsBD
							.consulta("SELECT id, codigo FROM gestion_biblioteca_material_bilbiografico WHERE id="
									+ rsGestionBiblioteca.getInt("id") + ";");
					if (rsListaMaterialBibliografico != null) {
						while (rsListaMaterialBibliografico.next())
							listaMaterialBibliografico.add(materialBibliograficoDao
									.obtenerPorCodigo(rsListaMaterialBibliografico.getString("codigo")));
						rsListaMaterialBibliografico.close();
					}
					listaGestionBiblioteca.add(new GestionBiblioteca(rsGestionBiblioteca.getInt("id"),
							rsGestionBiblioteca.getDate("fecha_prestamo"),
							rsGestionBiblioteca.getDate("fecha_devolucion"), persona, listaMaterialBibliografico));
				}
				rsGestionBiblioteca.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaGestionBiblioteca;
	}

	public GestionBiblioteca obtenerPorId(int id) {
		GestionBiblioteca gestionBiblioteca = null;
		try {
			ResultSet rsGestionBiblioteca = utilsBD.consulta(
					"SELECT id, fecha_prestamo, fecha_devolucion, cedula FROM gestion_biblioteca WHERE id=" + id + ";");
			if (rsGestionBiblioteca != null) {
				while (rsGestionBiblioteca.next()) {
					Persona persona = personaDao.obtenerPorCedula(rsGestionBiblioteca.getString("cedula"));
					List<MaterialBibliografico> listaMaterialBibliografico = new ArrayList<MaterialBibliografico>();
					ResultSet rsListaMaterialBibliografico = utilsBD.consulta(
							"SELECT id, codigo FROM gestion_biblioteca_material_bilbiografico WHERE id=" + id + ";");
					if (rsListaMaterialBibliografico != null) {
						while (rsListaMaterialBibliografico.next())
							listaMaterialBibliografico.add(
									materialBibliograficoDao.obtenerPorCodigo(rsGestionBiblioteca.getString("codigo")));
						rsListaMaterialBibliografico.close();
					}
					gestionBiblioteca = new GestionBiblioteca(rsGestionBiblioteca.getInt("id"),
							rsGestionBiblioteca.getDate("fecha_prestamo"),
							rsGestionBiblioteca.getDate("fecha_devolucion"), persona, listaMaterialBibliografico);
				}
				rsGestionBiblioteca.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gestionBiblioteca;
	}

	public String modificar(GestionBiblioteca gestionBiblioteca) {
		try {
			utilsBD.sentencia(
					"UPDATE gestion_biblioteca SET fecha_prestamo=?, fecha_devolucion=?, cedula=? WHERE id=?;",
					new Object[] { gestionBiblioteca.getFechaPrestamo(), gestionBiblioteca.getFechaDevolucion(),
							gestionBiblioteca.getPersona().getCedula(), gestionBiblioteca.getId() });
			utilsBD.sentencia("DELETE FROM gestion_biblioteca_material_bilbiografico WHERE id=?;",
					new Object[] { gestionBiblioteca.getId() });
			if (gestionBiblioteca.getListaMaterialBibliografico() != null)
				for (MaterialBibliografico mb : gestionBiblioteca.getListaMaterialBibliografico()) {
					utilsBD.sentencia("UPDATE gestion_biblioteca_material_bilbiografico SET codigo=? WHERE id=?;",
							new Object[] { mb.getCodigo(), gestionBiblioteca.getId() });
				}
		} catch (Exception e) {
			return "TNo se pudo modificar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe modifico correctamente la gestion biblioteca";
	}

	public String eliminar(GestionBiblioteca gestionBiblioteca) {
		try {
			utilsBD.sentencia("DELETE FROM gestion_biblioteca WHERE id=?;", new Object[] { gestionBiblioteca.getId() });
		} catch (Exception e) {
			return "TNo se pudo eliminar la gestion biblioteca, causa: " + e.getCause().getMessage();
		}
		return "TSe elimino correctamente la gestion biblioteca";
	}

}
