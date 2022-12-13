package com.soluciones.web.appGrupo4.helper;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class PaginationHelper implements IPaginationHelper{

    @Override
    public int getPageNumberIndex(Object page) {
        return page != null ? (Integer.valueOf(page.toString()) - 1 ) : 0;
    }

    @Override
    public List<Integer> pageNumberListGenerator(Integer totalPageNumber) {

        if (totalPageNumber > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPageNumber)
                                    .boxed().collect(Collectors.toList());
            return pages;
            
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Integer> pageInfoGnerator(Integer totalPageNumber, Integer pageIndexNumber) {

        Map<String, Integer> pagesInfo = new HashMap<>();

        pagesInfo.put("current", pageIndexNumber + 1);
        pagesInfo.put("next", pageIndexNumber + 2);
        pagesInfo.put("prev", pageIndexNumber);
        pagesInfo.put("last", totalPageNumber);

        return pagesInfo;
    };
    
}
