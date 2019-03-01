package com.marcskow.spring.bootstrap.java.search;

import com.marcskow.spring.bootstrap.java.common.Page;
import com.marcskow.spring.bootstrap.java.common.ResultPage;
import com.marcskow.spring.bootstrap.java.search.elastic.document.ElasticPerson;
import com.marcskow.spring.bootstrap.java.search.elastic.repository.ElasticPersonRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private ElasticPersonRepository elasticPersonRepository;

    public SearchService(ElasticPersonRepository elasticPersonRepository) {
        this.elasticPersonRepository = elasticPersonRepository;
    }

    public ResultPage<ElasticPerson> searchPersons(String firstName, Page page) {
        return new ResultPage<>(elasticPersonRepository.findByFirstName(firstName, page.asPageRequest()));
    }
}
