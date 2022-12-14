package com.soluciones.web.appGrupo4.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.modelo.Personas;
import com.soluciones.web.appGrupo4.repositorio.IPersonas;

@Service
public class PersonasService  implements IPersonasService{

	@Autowired
	private IPersonas data;
	
	@Override
	public List<Personas> listar() {
		
		return (List<Personas>)data.findAll();
	}

	@Override
	public Optional<Personas> listarId(int idperson) {
		
		return data.findById(idperson);
	}

	@Override
	public int save(Personas p) {
		int res = 0;
		
		Personas persona = data.save(p);
		
		if (!persona.equals(null)) {
			res = 1;
		}
		
		return res;
	}

	@Override
	public void delete(int idperson) {
		data.deleteById(idperson);
	}

}
