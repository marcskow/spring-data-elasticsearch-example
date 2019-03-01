package com.marcskow.spring.bootstrap.java.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
@AllArgsConstructor
public class Page {
    private final int pageNo;
    private final int size;

    public PageRequest asPageRequest() {
        return PageRequest.of(pageNo, size);
    }
}
