package com.template.cqrspattern.service;

import com.template.cqrspattern.domain.Product;
import com.template.cqrspattern.dto.ProductCreateReqeust;
import com.template.cqrspattern.dto.ProductGetResponse;
import com.template.cqrspattern.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long createProduct(ProductCreateReqeust productCreateReqeust) {
        Product product = Product.builder()
                .name(productCreateReqeust.getName())
                .price(productCreateReqeust.getPrice())
                .description(productCreateReqeust.getDescription())
                .build();
        return productRepository.save(product)
                .getId();
    }

    @Transactional(readOnly = true)
    public ProductGetResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid productId."));
        return ProductGetResponse.of(product);
    }
}
