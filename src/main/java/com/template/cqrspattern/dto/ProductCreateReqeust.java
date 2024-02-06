package com.template.cqrspattern.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductCreateReqeust {

    private final String name;
    private final Long price;
    private final String description;
}
