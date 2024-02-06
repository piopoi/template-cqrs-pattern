package com.template.cqrspattern.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private String description;

    protected Product() {
    }

    @Builder
    private Product(String name, Long price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
