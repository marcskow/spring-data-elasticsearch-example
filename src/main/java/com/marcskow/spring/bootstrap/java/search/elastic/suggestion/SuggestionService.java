package com.marcskow.spring.bootstrap.java.search.elastic.suggestion;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.phrase.DirectCandidateGeneratorBuilder;
import org.elasticsearch.search.suggest.phrase.PhraseSuggestionBuilder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    private Client client;

    public SuggestionService(Client client) {
        this.client = client;
    }

    public List<String> suggestLastName(String term) {
        PhraseSuggestionBuilder phraseSuggestionBuilder = SuggestBuilders
                .phraseSuggestion("lastName.lastNameTrigram")
                .addCandidateGenerator(
                        new DirectCandidateGeneratorBuilder("lastName.lastNameTrigram")
                                .minWordLength(2)
                )
                .maxErrors(30);

        SuggestBuilder suggestBuilder = new SuggestBuilder()
                .addSuggestion("last-name-suggestion", phraseSuggestionBuilder)
                .setGlobalText(term);

        SearchRequestBuilder builder = client.prepareSearch("elastic")
                .suggest(suggestBuilder);

        return getSuggestion(builder).map(suggestion ->
                suggestion.getSuggest().getSuggestion("last-name-suggestion")
                        .getEntries()
                        .stream()
                        .map(e -> e.getOptions().stream().map(o -> o.getText().toString()))
                        .flatMap(Function.identity())
                        .collect(Collectors.toList())
        ).orElse(Collections.emptyList());
    }

    private Optional<SearchResponse> getSuggestion(SearchRequestBuilder searchRequestBuilder) {
        try {
            return Optional.ofNullable(searchRequestBuilder.execute().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
