package gestionBiblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import gestionBiblioteca.entity.Libro;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Revista;
import gestionBiblioteca.entity.Tesis;
import gestionBiblioteca.utils.UtilsArchivos;

public class MaterialBibliograficoDaoImpl implements MaterialBibliograficoDao {

	private String bdMaterialBibliografico = UtilsArchivos.getBDMaterialBibliografico();

	public String insertar(MaterialBibliografico materialBibliografico) {
		try {
			if (materialBibliografico.getTipo() == 1)
				UtilsArchivos.insertar(bdMaterialBibliografico, ((Tesis) materialBibliografico).toString());
			else if (materialBibliografico.getTipo() == 2)
				UtilsArchivos.insertar(bdMaterialBibliografico, ((Revista) materialBibliografico).toString());
			else
				UtilsArchivos.insertar(bdMaterialBibliografico, ((Libro) materialBibliografico).toString());
		} catch (Exception e) {
			return "FNo se pudo ingresar el material bibliografico, causa: " + e.getCause().getMessage();
		}
		return "TSe ingreso correctamente el material bibliografico";
	}

	public List<MaterialBibliografico> obtenerTodos() {
		List<MaterialBibliografico> listaMaterialBibliografico = new ArrayList<MaterialBibliografico>();
		try {
			for (String dato : UtilsArchivos.obtenerTodos(bdMaterialBibliografico)) {
				String datoAux[] = dato.split(",");
				int tipo = Integer.parseInt(datoAux[0]);
				if (tipo == 1)
					listaMaterialBibliografico.add(new Tesis(datoAux));
				else if (tipo == 2)
					listaMaterialBibliografico.add(new Revista(datoAux));
				else
					listaMaterialBibliografico.add(new Libro(datoAux));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaMaterialBibliografico;
	}

	public MaterialBibliografico obtenerPorCodigo(String codigo) {
		for (MaterialBibliografico materialBibliografico : obtenerTodos())
			if (materialBibliografico.getCodigo().compareToIgnoreCase(codigo) == 0)
				return materialBibliografico;
		return null;
	}

	public String modificar(MaterialBibliografico materialBibliografico) {
		try {
			List<MaterialBibliografico> listaMaterialBibliografico = obtenerTodos();
			listaMaterialBibliografico.set(listaMaterialBibliografico.indexOf(materialBibliografico),
					materialBibliografico);
			UtilsArchivos.modificarEliminar(bdMaterialBibliografico,
					UtilsArchivos.generarListaGuardar(listaMaterialBibliografico));
		} catch (Exception e) {
			return "FNo se pudo modificar el material bibliografico, causa: " + e.getCause().getMessage();
		}
		return "TSe modifico correctamente el material bibliografico";
	}

	public String eliminar(MaterialBibliografico materialBibliografico) {
		try {
			List<MaterialBibliografico> listaMaterialBibliografico = obtenerTodos();
			listaMaterialBibliografico.remove(materialBibliografico);
			UtilsArchivos.modificarEliminar(bdMaterialBibliografico,
					UtilsArchivos.generarListaGuardar(listaMaterialBibliografico));
		} catch (Exception e) {
			return "FNo se pudo eliminar el material bibliografico: " + e.getCause().getMessage();
		}
		return "TSe elimino correctamente el material bibliografico";
	}

}
