package gestionBiblioteca.service;

import java.util.List;

import gestionBiblioteca.dao.MaterialBibliograficoDao;
import gestionBiblioteca.dao.MaterialBibliograficoDaoImpl;
import gestionBiblioteca.entity.Libro;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Revista;
import gestionBiblioteca.entity.Tesis;

public class MaterialBibliograficoServiceImpl implements MaterialBibliograficoService {

	private MaterialBibliograficoDao materialBibliograficoDao = new MaterialBibliograficoDaoImpl();

	public String insertar(MaterialBibliografico materialBibliografico) {
		String mensaje = "";
		if ((mensaje = validarMaterialBibliografico(materialBibliografico)) != null)
			return mensaje;
		else if (obtenerPorCodigo(materialBibliografico.getCodigo()) != null)
			return "FEl material bibliografico que va a ingresar ya existe";
		else {
			if (materialBibliografico.getTipo() == 1) {
				Tesis tesis = (Tesis) materialBibliografico;
				tesis.setAutor(tesis.getAutor().toUpperCase().replace(",", ""));
				tesis.setTutor(tesis.getTutor().toUpperCase().replace(",", ""));
				materialBibliografico = tesis;
			} else if (materialBibliografico.getTipo() == 2) {
				Revista revista = (Revista) materialBibliografico;
				revista.setTipoRevista(revista.getTipoRevista().toUpperCase().replace(",", ""));
				materialBibliografico = revista;
			} else {
				Libro libro = (Libro) materialBibliografico;
				libro.setEditorial(libro.getEditorial().toUpperCase().replace(",", ""));
				libro.setAutor(libro.getAutor().toUpperCase().replace(",", ""));
				materialBibliografico = libro;
			}
			materialBibliografico.setCodigo(materialBibliografico.getCodigo().toUpperCase().replace(",", ""));
			materialBibliografico.setTitulo(materialBibliografico.getTitulo().toUpperCase().replace(",", ""));
			return materialBibliograficoDao.insertar(materialBibliografico);
		}
	}

	public List<MaterialBibliografico> obtenerTodos() {
		return materialBibliograficoDao.obtenerTodos();
	}

	public MaterialBibliografico obtenerPorCodigo(String codigo) {
		return materialBibliograficoDao.obtenerPorCodigo(codigo);
	}

	public String modificar(MaterialBibliografico materialBibliografico) {
		String mensaje = "";
		if ((mensaje = validarMaterialBibliografico(materialBibliografico)) != null)
			return mensaje;
		else {
			if (materialBibliografico.getTipo() == 1) {
				Tesis tesis = (Tesis) materialBibliografico;
				tesis.setAutor(tesis.getAutor().toUpperCase().replace(",", ""));
				tesis.setTutor(tesis.getTutor().toUpperCase().replace(",", ""));
				materialBibliografico = tesis;
			} else if (materialBibliografico.getTipo() == 2) {
				Revista revista = (Revista) materialBibliografico;
				revista.setTipoRevista(revista.getTipoRevista().toUpperCase().replace(",", ""));
				materialBibliografico = revista;
			} else {
				Libro libro = (Libro) materialBibliografico;
				libro.setEditorial(libro.getEditorial().toUpperCase().replace(",", ""));
				libro.setAutor(libro.getAutor().toUpperCase().replace(",", ""));
				materialBibliografico = libro;
			}
			materialBibliografico.setCodigo(materialBibliografico.getCodigo().toUpperCase().replace(",", ""));
			materialBibliografico.setTitulo(materialBibliografico.getTitulo().toUpperCase().replace(",", ""));
			return materialBibliograficoDao.modificar(materialBibliografico);
		}
	}

	public String eliminar(MaterialBibliografico materialBibliografico) {
		if (materialBibliografico == null)
			return "FEscoja una material bibliografico para eliminar";
		else
			return materialBibliograficoDao.eliminar(materialBibliografico);
	}

	private String validarMaterialBibliografico(MaterialBibliografico mb) {
		if (mb == null)
			return "FEscoja un material bibliografico para insertar";
		else if (mb.getCodigo() == null || mb.getCodigo().compareToIgnoreCase("") == 0)
			return "FIngrese el codigo del material bibliografico";
		else if (mb.getTitulo() == null || mb.getTitulo().compareToIgnoreCase("") == 0)
			return "FIngrese el titulo del material bibliografico";
		else if (mb.getAnioPublicacion() == 0)
			return "FIngrese el anio de publicacion del material bibliografico";
		else if (mb.getUnidades() == 0)
			return "FIngrese el numero de unidades del material bibliografico";
		else if (mb.getTipo() == 0)
			return "FIngrese el tipo del material bibliografico";
		else {
			if (mb.getTipo() == 1) {
				Tesis tesis = (Tesis) mb;
				if (tesis.getAutor() == null || tesis.getAutor().compareToIgnoreCase("") == 0)
					return "FIngrese el autor para la tesis";
				else if (tesis.getTutor() == null || tesis.getTutor().compareToIgnoreCase("") == 0)
					return "FIngrese el tutor para la tesis";
			} else if (mb.getTipo() == 2) {
				Revista revista = (Revista) mb;
				if (revista.getFechaPublicacion() == null)
					return "FIngrese la fecha de publicacion para la revista en formato dd/MM/yyyy";
				else if (revista.getTipoRevista() == null || revista.getTipoRevista().compareToIgnoreCase("") == 0)
					return "FIngrese el tipo de revista para la revista";
			} else {
				Libro libro = (Libro) mb;
				if (libro.getEditorial() == null || libro.getEditorial().compareToIgnoreCase("") == 0)
					return "FIngrese la editorial para el libro";
				else if (libro.getAutor() == null || libro.getAutor().compareToIgnoreCase("") == 0)
					return "FIngrese el autor para el libro";
				else if (libro.getEdicion() == 0)
					return "FIngrese la edicion para el libro";
			}
		}
		return null;
	}

}
