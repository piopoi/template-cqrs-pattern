package com.template.cqrspattern.dto;

import com.template.cqrspattern.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductGetResponse {

    private final Long id;
    private final String name;
    private final Long price;
    private final String description;

    @Builder
    public ProductGetResponse(Long id, String name, Long price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static ProductGetResponse of(Product product) {
        return ProductGetResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
