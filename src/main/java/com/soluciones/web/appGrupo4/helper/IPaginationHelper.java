package com.soluciones.web.appGrupo4.helper;

import java.util.List;
import java.util.Map;

public interface IPaginationHelper {
    
    public int getPageNumberIndex(Object page);

    public List<Integer> pageNumberListGenerator(Integer totalPageNumber);

    public Map<String, Integer> pageInfoGnerator(Integer totalPageNumber, Integer pageIndexNumber);

}
