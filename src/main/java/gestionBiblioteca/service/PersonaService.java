package gestionBiblioteca.service;

import java.util.List;

import gestionBiblioteca.entity.Persona;

public interface PersonaService {

	public String insertar(Persona persona);

	public List<Persona> obtenerTodos();

	public Persona obtenerPorCedula(String cedula);

	public String modificar(Persona persona);

	public String eliminar(Persona persona);

}
