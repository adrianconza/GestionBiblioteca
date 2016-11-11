package gestionBiblioteca.dao;

import java.util.List;

import gestionBiblioteca.entity.GestionBiblioteca;

public interface GestionBibliotecaDao {

	public String insertar(GestionBiblioteca gestionBiblioteca);

	public List<GestionBiblioteca> obtenerTodos();

	public GestionBiblioteca obtenerPorCedula(String cedula);

	public String modificar(GestionBiblioteca gestionBiblioteca);

	public String eliminar(GestionBiblioteca gestionBiblioteca);

}
