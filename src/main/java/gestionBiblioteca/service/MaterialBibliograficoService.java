package gestionBiblioteca.service;

import java.util.List;

import gestionBiblioteca.entity.MaterialBibliografico;

public interface MaterialBibliograficoService {

	public String insertar(MaterialBibliografico materialBibliografico);

	public List<MaterialBibliografico> obtenerTodos();

	public MaterialBibliografico obtenerPorCodigo(String codigo);

	public String modificar(MaterialBibliografico materialBibliografico);

	public String eliminar(MaterialBibliografico materialBibliografico);

}
