package com.marcskow.spring.bootstrap.java.api;

import com.marcskow.spring.bootstrap.java.search.elastic.ReIndexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config/elastic")
public class ElasticController {

    private ReIndexService reIndexService;

    public ElasticController(ReIndexService reIndexService) {
        this.reIndexService = reIndexService;
    }

    @GetMapping("/reindex")
    public ResponseEntity<?> reindexElasticRepositories() {
        reIndexService.reindexPersonRepository();
        return ResponseEntity.ok().build();
    }
}
