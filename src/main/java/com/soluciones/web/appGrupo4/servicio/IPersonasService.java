package com.soluciones.web.appGrupo4.servicio;

import java.util.List;
import java.util.Optional;

import com.soluciones.web.appGrupo4.modelo.Personas;


public interface IPersonasService {

	public List<Personas>listar();
	public Optional<Personas>listarId(int idPerson);
	public int save(Personas p);
	public void delete(int idPerson);
}
