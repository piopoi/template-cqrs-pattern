package com.template.cqrspattern.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.cqrspattern.domain.Product;
import com.template.cqrspattern.repository.ProductRepository;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    private final String requestUri = "/api/products";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품을 생성할 수 있다.")
    void createProduct() throws Exception {
        //given
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "노트북");
        params.put("price", 2000000);
        params.put("description", "테스트 브랜드의 개발용 노트북입니다.");

        //when then
        mockMvc.perform(post(requestUri)
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(params))
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("상품아이디로 상품을 조회할 수 있다.")
    void getProduct() throws Exception {
        //given
        Product savedProduct = productRepository.save(Product.builder()
                .name("노트북")
                .price(1000000L)
                .description("테스트 브랜드의 개발용 노트북입니다.")
                .build());

        //when then
        mockMvc.perform(get(requestUri + "/{productId}", savedProduct.getId())
                        .accept(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedProduct.getId()))
                .andExpect(jsonPath("$.name").value(savedProduct.getName()))
                .andExpect(jsonPath("$.price").value(savedProduct.getPrice()))
                .andExpect(jsonPath("$.description").value(savedProduct.getDescription()));
    }
}
