package com.soluciones.web.appGrupo4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.UserList;
import com.soluciones.web.appGrupo4.model.entities.E_Country;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.service.IListService;
import com.soluciones.web.appGrupo4.service.interfaces.ICountryService;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;
import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;

@Controller
@RequestMapping("/app")
public class UserPageController {

    @Value("${web.title}")
    private String title;

    @Value("${image.resource.path.general}" + "${image.folder.movie.name}" + "/")
    private String movieImagePath;

    @Value("${pagination.user.size}")
    private int pageNumbers;

    @Autowired
    private ITrailerService trailerInterface;

    @Autowired
    private ILanguageService languageInterface;

    @Autowired
    private IGenreService genreInterface;

    @Autowired
    private ICountryService countriesInterface;

    @Autowired
    private IListService listsInterface;
   
    @GetMapping("/trailers")
    public String allTrailers(Model model) {

        model.addAttribute("activeSession", true);

        Response<E_Trailer> trailerResponse = trailerInterface.getAllTrailers();
        Response<E_Language> languageResponse = languageInterface.getAllLanguages();
        Response<E_Genre> genreResponse = genreInterface.getAllGenres();
        Response<E_Country> countryResponse = countriesInterface.getAllCountries();

        if (trailerResponse.getState() && languageResponse.getState()) {
			model.addAttribute("title", title + " | Trailers");
			model.addAttribute("trailersList", trailerResponse.getListData());
			model.addAttribute("response", trailerResponse.getMessage());
            model.addAttribute("languagesList", languageResponse.getListData());
            model.addAttribute("genresList", genreResponse.getListData());
            model.addAttribute("countriesList", countryResponse.getListData());
			return "trailers";
		} else {

            List<String> errorsHeader = new ArrayList<>();
            List<String> errorsBody = new ArrayList<>();

            if (trailerResponse.getErrorMessage() != "") {
                errorsHeader.add(trailerResponse.getMessage());
                errorsBody.add(trailerResponse.getErrorMessage());
            }
            if (languageResponse.getErrorMessage() != "") {
                errorsHeader.add(languageResponse.getMessage());
                errorsBody.add(languageResponse.getErrorMessage());
            }
            if (genreResponse.getErrorMessage() != "") {
                errorsHeader.add(genreResponse.getMessage());
                errorsBody.add(genreResponse.getErrorMessage());
            }
            if (countryResponse.getErrorMessage() != "") {
                errorsHeader.add(countryResponse.getMessage());
                errorsBody.add(countryResponse.getErrorMessage());
            }

			model.addAttribute("title", title + " | Error al obtener trailers");
			model.addAttribute("errorsHeader", errorsHeader);
			model.addAttribute("errorsBody", errorsBody);
			return "errors";
		}
    };

    @GetMapping("/trailer/view/{id}")
    public String trailerView(@PathVariable String id, Model model) {

        model.addAttribute("activeSession", true);

        Response<E_Trailer> trailerResponse = trailerInterface.getTrailerById(id);
        Response<E_Trailer> relatedTrailersResponse = trailerInterface.getRelatedTrailers(id);

        if (trailerResponse.getState() && relatedTrailersResponse.getState()) {
			model.addAttribute("title", title + " | " + trailerResponse.getData().getTitle());
			model.addAttribute("trailer", trailerResponse.getData());
            model.addAttribute("relatedTrailers", relatedTrailersResponse.getListData());
            model.addAttribute("movieImagePath", movieImagePath);
			model.addAttribute("response", trailerResponse.getMessage());
            // TemporallyData
            model.addAttribute("userList", listsInterface.getAllLists());

			return "trailer_view";

		} else {

            List<String> errorsHeader = new ArrayList<>();
            List<String> errorsBody = new ArrayList<>();

            if (trailerResponse.getErrorMessage() != "") {
                errorsHeader.add(trailerResponse.getMessage());
                errorsBody.add(trailerResponse.getErrorMessage());
            }
            if (relatedTrailersResponse.getErrorMessage() != "") {
                errorsHeader.add(relatedTrailersResponse.getMessage());
                errorsBody.add(relatedTrailersResponse.getErrorMessage());
            }

			model.addAttribute("title", title + " | Error al obtener el trailer");
			model.addAttribute("errorsHeader", errorsHeader);
			model.addAttribute("errorsBody", errorsBody);
			return "errors";
		}
    };





    // Temporally function (whitout db) <=============================
    @PostMapping("/list/createNewList/{id}")
    public String createNewList(UserList usrList, @PathVariable String id) {
        System.out.println("*** ---> Lista creada");
        System.out.println(usrList.getName());

        // Redirect to same page
        return "redirect:/app/trailer/view/" + id;
    };




    // Pageable

    @GetMapping("/trailersPaginated")
    public String paginatedTrailers(@RequestParam Map<String, Object> params, Model model) {

        model.addAttribute("activeSession", true);

        // Patio de Juegos

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1 ) : 0;
        PageRequest pageRequest = PageRequest.of(page, pageNumbers);

        Response<E_Trailer> trailerResponsePaginated = trailerInterface.getPaginatedTrailers(pageRequest);

        Response<E_Language> languageResponse = languageInterface.getAllLanguages();
        Response<E_Genre> genreResponse = genreInterface.getAllGenres();
        Response<E_Country> countryResponse = countriesInterface.getAllCountries();

        if (trailerResponsePaginated.getState()) {

            Page<E_Trailer> pageTrailer = trailerResponsePaginated.getPaginatedData();

            int totalPages = pageTrailer.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pages = IntStream.rangeClosed(1, totalPages)
                                        .boxed().collect(Collectors.toList());
                model.addAttribute("pages", pages);
            }
            model.addAttribute("current", page + 1);
            model.addAttribute("next", page + 2);
            model.addAttribute("prev", page);
            model.addAttribute("last", totalPages);

            model.addAttribute("title", title + " | Trailers");
			model.addAttribute("trailersList", pageTrailer.getContent());
			model.addAttribute("response", trailerResponsePaginated.getMessage());
            model.addAttribute("languagesList", languageResponse.getListData());
            model.addAttribute("genresList", genreResponse.getListData());
            model.addAttribute("countriesList", countryResponse.getListData());

            return "trailers";
        } else {

            List<String> errorsHeader = new ArrayList<>();
            List<String> errorsBody = new ArrayList<>();

            if (trailerResponsePaginated.getErrorMessage() != "") {
                errorsHeader.add(trailerResponsePaginated.getMessage());
                errorsBody.add(trailerResponsePaginated.getErrorMessage());
            }
            if (languageResponse.getErrorMessage() != "") {
                errorsHeader.add(languageResponse.getMessage());
                errorsBody.add(languageResponse.getErrorMessage());
            }
            if (genreResponse.getErrorMessage() != "") {
                errorsHeader.add(genreResponse.getMessage());
                errorsBody.add(genreResponse.getErrorMessage());
            }
            if (countryResponse.getErrorMessage() != "") {
                errorsHeader.add(countryResponse.getMessage());
                errorsBody.add(countryResponse.getErrorMessage());
            }

			model.addAttribute("title", title + " | Error al obtener trailers");
			model.addAttribute("errorsHeader", errorsHeader);
			model.addAttribute("errorsBody", errorsBody);
			return "errors";
		}

        // Fin del Patio de Juegos
        // ========================
        // ========================
        // ========================
        // ========================
        // ========================
        // ========================

    };

}
