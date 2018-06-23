package com.sda.java.gda.springdemo.controller;

import com.sda.java.gda.springdemo.model.Product;
import com.sda.java.gda.springdemo.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @MockBean
    private ProductService productService;

    private Product product;

    @Before
    public void before() {
        product = new Product("name", 10d);
    }

    @Test
    public void shouldGetProductById() {
        Long id = 1L;
        when(productService.getById(any()))
                .thenReturn(product);

        Product result = given()
                .port(port)
                .when()
                .get("/products/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Product.class);

        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());

    }
}
