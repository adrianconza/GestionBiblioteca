package gestionBiblioteca.dao;

import java.util.List;

import gestionBiblioteca.entity.MaterialBibliografico;

public interface MaterialBibliograficoDao {

	public String insertar(MaterialBibliografico materialBibliografico);

	public List<MaterialBibliografico> obtenerTodos();

	public MaterialBibliografico obtenerPorCedula(String codigo);

	public String modificar(MaterialBibliografico materialBibliografico);

	public String eliminar(MaterialBibliografico materialBibliografico);

}
