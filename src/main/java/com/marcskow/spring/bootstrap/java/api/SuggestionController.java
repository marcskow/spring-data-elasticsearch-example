package com.marcskow.spring.bootstrap.java.api;

import com.marcskow.spring.bootstrap.java.search.elastic.suggestion.SuggestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suggest")
public class SuggestionController {

    private SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping
    public List<String> getLastNameSuggestion(@RequestParam String lastName) {
        return suggestionService.suggestLastName(lastName);
    }
}
