package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.helper.ICreateDefaultObjects;
import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.validators.V_Language;
import com.soluciones.web.appGrupo4.repository.I_language_db;
import com.soluciones.web.appGrupo4.repository.manage.ILanguage;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;

@Service
public class LanguageService implements ILanguageService {

    @Autowired
    private I_language_db language_entity;

    @Autowired
    private ILanguage language_modify;

	@Autowired
	private ICreateDefaultObjects createDefaultObjects;

    @Override
    public Response<E_Language> getAllLanguages() {

        Response<E_Language> response = new Response<>();

		try {

			List<E_Language> languagesList = language_entity.findByOrderByNameLanguageAsc();

			if (languagesList.size() > 0) {
				response.setListData( languagesList );

			} else {
				Response<E_Language> createLanguagesResponse = createDefaultObjects.createDefaultLanguages();
				if (createLanguagesResponse.getState()) {
					response.setListData( language_entity.findByOrderByNameLanguageAsc() );
				} else {
					response.setState(false);
					response.setMessage("Hubo problemas para obtener el listado de lenguajes");
					response.setErrorMessage("No se pudo crear los mensajes por defecto");
					return response;
				}
			}

			response.setMessage("Lista de Lenguajes");
			response.setState(true);

			response.setListData( (List<E_Language>)language_entity.findByOrderByNameLanguageAsc() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado de lenguajes");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

    @Override
    public Response<V_Language> getLazyInfoLanguage() {
        
        Response<V_Language> response = new Response<>();

		try {

			List<V_Language> languagesList = language_modify.findAll();

			if (languagesList.size() > 0) {
				response.setListData( languagesList );

			} else {
				Response<E_Language> createLanguagesResponse = createDefaultObjects.createDefaultLanguages();
				if (createLanguagesResponse.getState()) {
					response.setListData( language_modify.findAll() );
				} else {
					response.setState(false);
					response.setMessage("Hubo problemas para obtener el listado de lenguajes");
					response.setErrorMessage("No se pudo crear los mensajes por defecto");
					return response;
				}
			}

			response.setMessage("Datos lenguajes obtenidos correctamente");
			response.setState(true);
			// response.setListData( (List<V_Language>)language_modify.findAll() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener los datos de los lenguajes");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

	@Override
	public Response<E_Language> getLanguageById( String id ) {

		Response<E_Language> response = new Response<>();

    	try {
        	Optional<E_Language> targetLanguage = language_entity.findById(id);

            response.setState(true);
			response.setData(targetLanguage.get());
			response.setMessage("Lenguaje encontrado: " + targetLanguage.get().getNameLanguage());

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar el lenguaje con el ID: " + id);
			response.setErrorMessage(e.getStackTrace().toString());
        }

        return response;
	};
    
}
