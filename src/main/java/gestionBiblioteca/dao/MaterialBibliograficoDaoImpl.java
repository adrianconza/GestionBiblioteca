package gestionBiblioteca.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gestionBiblioteca.entity.Libro;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Revista;
import gestionBiblioteca.entity.Tesis;
import gestionBiblioteca.utils.UtilsBD;

public class MaterialBibliograficoDaoImpl implements MaterialBibliograficoDao {

	private UtilsBD utilsBD = UtilsBD.getInstance();

	public String insertar(MaterialBibliografico materialBibliografico) {
		try {
			utilsBD.sentencia(
					"INSERT INTO material_bibliografico(codigo, titulo, unidades, anio_publicacion, tipo) VALUES (?, ?, ?, ?, ?);",
					new Object[] { materialBibliografico.getCodigo(), materialBibliografico.getTitulo(),
							materialBibliografico.getUnidades(), materialBibliografico.getAnioPublicacion(),
							materialBibliografico.getTipo() });
			if (materialBibliografico.getTipo() == 1) {
				Tesis tesis = (Tesis) materialBibliografico;
				utilsBD.sentencia("INSERT INTO tesis(codigo, tutor, autor) VALUES (?, ?, ?);",
						new Object[] { tesis.getCodigo(), tesis.getTutor(), tesis.getTutor() });
			} else if (materialBibliografico.getTipo() == 2) {
				Revista revista = (Revista) materialBibliografico;
				utilsBD.sentencia("INSERT INTO revista(codigo, fecha_publicacion, tipo_revista) VALUES (?, ?, ?);",
						new Object[] { revista.getCodigo(), revista.getFechaPublicacion(), revista.getTipoRevista() });
			} else {
				Libro libro = (Libro) materialBibliografico;
				utilsBD.sentencia("INSERT INTO libro(codigo, editorial, edicion, autor) VALUES (?, ?, ?, ?);",
						new Object[] { libro.getCodigo(), libro.getEditorial(), libro.getEdicion(), libro.getAutor() });
			}
		} catch (Exception e) {
			return "FNo se pudo ingresar el material bibliografico, causa: " + e.getMessage();
		}
		return "TSe ingreso correctamente el material bibliografico";
	}

	public List<MaterialBibliografico> obtenerTodos() {
		List<MaterialBibliografico> listaMaterialBibliografico = new ArrayList<MaterialBibliografico>();
		try {
			ResultSet rsTesis = utilsBD.consulta(
					"SELECT material_bibliografico.*, tesis.tutor, tesis.autor FROM material_bibliografico, tesis WHERE tipo=1;");
			if (rsTesis != null) {
				while (rsTesis.next())
					listaMaterialBibliografico.add(new Tesis(rsTesis.getString("codigo"), rsTesis.getString("titulo"),
							rsTesis.getInt("anio_publicacion"), rsTesis.getInt("unidades"), rsTesis.getInt("tipo"),
							rsTesis.getString("autor"), rsTesis.getString("tutor")));

				rsTesis.close();
			}
			ResultSet rsRevista = utilsBD.consulta(
					"SELECT material_bibliografico.*, revista.fecha_publicacion, revista.tipo_revista FROM material_bibliografico, revista WHERE tipo=2;");
			if (rsRevista != null) {
				while (rsRevista.next())
					listaMaterialBibliografico.add(new Revista(rsRevista.getString("codigo"),
							rsRevista.getString("titulo"), rsRevista.getInt("anio_publicacion"),
							rsRevista.getInt("unidades"), rsRevista.getInt("tipo"),
							rsRevista.getDate("fecha_publicacion"), rsRevista.getString("tipo_revista")));
				rsRevista.close();
			}
			ResultSet rsLibro = utilsBD.consulta(
					"SELECT material_bibliografico.*, libro.editorial, libro.edicion, libro.autor FROM material_bibliografico, libro WHERE tipo=3;");
			if (rsLibro != null) {
				while (rsLibro.next())
					listaMaterialBibliografico.add(new Libro(rsLibro.getString("codigo"), rsLibro.getString("titulo"),
							rsLibro.getInt("anio_publicacion"), rsLibro.getInt("unidades"), rsLibro.getInt("tipo"),
							rsLibro.getString("editorial"), rsLibro.getString("autor"), rsLibro.getInt("edicion")));
				rsLibro.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaMaterialBibliografico;
	}

	public MaterialBibliografico obtenerPorCodigo(String codigo) {
		MaterialBibliografico materialBibliografico = null;
		try {
			ResultSet rsMaterialBibliografico = utilsBD
					.consulta("SELECT * FROM material_bibliografico where codigo='" + codigo + "';");
			if (rsMaterialBibliografico != null) {
				while (rsMaterialBibliografico.next()) {
					int tipo = rsMaterialBibliografico.getInt("tipo");
					if (tipo == 1) {
						ResultSet rsTesis = utilsBD.consulta("SELECT * FROM tesis WHERE codigo='" + codigo + "';");
						if (rsTesis != null) {
							while (rsTesis.next())
								materialBibliografico = new Tesis(rsMaterialBibliografico.getString("codigo"),
										rsMaterialBibliografico.getString("titulo"),
										rsMaterialBibliografico.getInt("anio_publicacion"),
										rsMaterialBibliografico.getInt("unidades"),
										rsMaterialBibliografico.getInt("tipo"), rsTesis.getString("autor"),
										rsTesis.getString("tutor"));

							rsTesis.close();
						}
					} else if (tipo == 2) {
						ResultSet rsRevista = utilsBD.consulta("SELECT * FROM revista WHERE codigo='" + codigo + "';");
						if (rsRevista != null) {
							while (rsRevista.next())
								materialBibliografico = new Revista(rsMaterialBibliografico.getString("codigo"),
										rsMaterialBibliografico.getString("titulo"),
										rsMaterialBibliografico.getInt("anio_publicacion"),
										rsMaterialBibliografico.getInt("unidades"),
										rsMaterialBibliografico.getInt("tipo"), rsRevista.getDate("fecha_publicacion"),
										rsRevista.getString("tipo_revista"));
							rsRevista.close();
						}
					} else if (tipo == 3) {
						ResultSet rsLibro = utilsBD.consulta("SELECT * FROM libro WHERE codigo='" + codigo + "';");
						if (rsLibro != null) {
							while (rsLibro.next())
								materialBibliografico = new Libro(rsMaterialBibliografico.getString("codigo"),
										rsMaterialBibliografico.getString("titulo"),
										rsMaterialBibliografico.getInt("anio_publicacion"),
										rsMaterialBibliografico.getInt("unidades"),
										rsMaterialBibliografico.getInt("tipo"), rsLibro.getString("editorial"),
										rsLibro.getString("autor"), rsLibro.getInt("edicion"));
							rsLibro.close();
						}
					}
				}
				rsMaterialBibliografico.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return materialBibliografico;
	}

	public String modificar(MaterialBibliografico materialBibliografico) {
		try {
			utilsBD.sentencia(
					"UPDATE material_bibliografico SET titulo=?, unidades=?, anio_publicacion=?, tipo=? WHERE codigo=?;",
					new Object[] { materialBibliografico.getTitulo(), materialBibliografico.getUnidades(),
							materialBibliografico.getAnioPublicacion(), materialBibliografico.getTipo(),
							materialBibliografico.getCodigo() });
			if (materialBibliografico.getTipo() == 1) {
				Tesis tesis = (Tesis) materialBibliografico;
				utilsBD.sentencia("UPDATE tesis SET tutor=?, autor=? WHERE codigo=?;",
						new Object[] { tesis.getTutor(), tesis.getAutor(), tesis.getCodigo() });
			} else if (materialBibliografico.getTipo() == 2) {
				Revista revista = (Revista) materialBibliografico;
				utilsBD.sentencia("UPDATE revista SET fecha_publicacion=?, tipo_revista=? WHERE codigo=?;",
						new Object[] { revista.getFechaPublicacion(), revista.getTipoRevista(), revista.getCodigo() });
			} else if (materialBibliografico.getTipo() == 3) {
				Libro libro = (Libro) materialBibliografico;
				utilsBD.sentencia("UPDATE libro SET editorial=?, edicion=?, autor=? WHERE codigo=?;",
						new Object[] { libro.getEditorial(), libro.getEdicion(), libro.getAutor(), libro.getCodigo() });
			}
		} catch (Exception e) {
			return "FNo se pudo modificar el material bibliografico, causa: " + e.getMessage();
		}
		return "TSe modifico correctamente el material bibliografico";
	}

	public String eliminar(MaterialBibliografico materialBibliografico) {
		try {
			utilsBD.sentencia("DELETE FROM material_bibliografico WHERE codigo=?;",
					new Object[] { materialBibliografico.getCodigo() });
		} catch (Exception e) {
			return "FNo se pudo eliminar el material bibliografico: " + e.getMessage();
		}
		return "TSe elimino correctamente el material bibliografico";
	}

}
