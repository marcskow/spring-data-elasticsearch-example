package com.marcskow.spring.bootstrap.java.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ResultPage<T> {
    private final List<T> results;
    private final long total;

    public ResultPage(List<T> content, long total) {
        this.results = content;
        this.total = total;
    }

    public ResultPage(Page<T> page) {
        this.results = page.getContent();
        this.total = page.getTotalElements();
    }
}
