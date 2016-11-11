package gestionBiblioteca.service;

import java.util.List;

import gestionBiblioteca.entity.GestionBiblioteca;

public interface GestionBibliotecaService {

	public String insertar(GestionBiblioteca gestionBiblioteca);

	public List<GestionBiblioteca> obtenerTodos();

	public GestionBiblioteca obtenerPorId(int id);

	public String modificar(GestionBiblioteca gestionBiblioteca);

	public String eliminar(GestionBiblioteca gestionBiblioteca);

}
