package com.marcskow.spring.bootstrap.java.api;

import com.marcskow.spring.bootstrap.java.common.Page;
import com.marcskow.spring.bootstrap.java.common.ResultPage;
import com.marcskow.spring.bootstrap.java.search.SearchService;
import com.marcskow.spring.bootstrap.java.search.elastic.document.ElasticPerson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResultPage<ElasticPerson> searchPersons(@RequestParam String firstName,
                                                   @RequestParam int page,
                                                   @RequestParam int size) {
        return searchService.searchPersons(firstName, new Page(page, size));
    }
}
