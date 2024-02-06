package com.template.cqrspattern.repository;

import com.template.cqrspattern.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
