package com.template.cqrspattern.controller;

import com.template.cqrspattern.dto.ProductCreateReqeust;
import com.template.cqrspattern.dto.ProductGetResponse;
import com.template.cqrspattern.service.ProductService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<Void> createProduct(@RequestBody ProductCreateReqeust productCreateReqeust) {
        Long productId = productService.createProduct(productCreateReqeust);
        return ResponseEntity.created(URI.create("/api/products/" + productId)).build();
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ProductGetResponse> getProduct(@PathVariable Long productId) {
        ProductGetResponse productGetResponse = productService.getProduct(productId);
        return ResponseEntity.ok(productGetResponse);
    }
}
