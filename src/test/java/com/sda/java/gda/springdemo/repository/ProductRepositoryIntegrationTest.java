package com.sda.java.gda.springdemo.repository;


import com.sda.java.gda.springdemo.controller.ReceiptController;
import com.sda.java.gda.springdemo.model.Product;
import com.sda.java.gda.springdemo.model.Receipt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReceiptRepository receiptRepository;


    private Product product;

    private Receipt receipt;

    @Before
    public void setup(){
        product = productRepository.save(new Product("name", 10d));
    }

    @Test
    public void shouldFindProductById(){
        assertEquals(product, productRepository.findById(product.getId()).get());
    }

    @Test
    public void shouldFindByName(){
        Product product1 = productRepository.save(new Product("bbNAME", 10d));
        Product product2 = productRepository.save(new Product("bbhohonAmE", 10d));
        Product product3 = productRepository.save(new Product("bbsome-name", 10d));

        List<Product> result = productRepository.findByNameContainingIgnoreCase("name");

        assertThat(result, hasItems(product, product1, product2, product3));
    }

    @Test
    public void shouldFindPageOfProductsByNameAndPrice(){
        Product product1 = productRepository.save(new Product("bbNAMrrrE", 1d));
        Product product2 = productRepository.save(new Product("bbhohonmE", 10d));
        Product product3 = productRepository.save(new Product("bbsomasae-name", 12d));
        Product product4 = productRepository.save(new Product("bbsasdfome-name", 11d));
        Product product5 = productRepository.save(new Product("bbse-namrrewe", 100d));

        Pageable pageable = new PageRequest(1, 1);

        Page<Product> result = productRepository.findByNameContainingIgnoreCaseAndPriceGreaterThanEqualAndPriceLessThanEqual(
                "name", 10d, 20d, pageable
        );

        assertThat(result.getContent(), hasItems(product3));
    }


//    @Test
//    public void shouldFindProductsByName(){
//        assertEquals(0, productRepository.search("asdf").size());
//
//        assertThat(productRepository.search("name"), hasItems(product));
//    }

    @Test
    public void shouldFindProductsByNameWithJoins(){
        Receipt receipt = receiptRepository.save(new Receipt("Tomek1", LocalDateTime.now(), Collections.singletonList(product)));

        assertThat(productRepository.search("name"), hasItems(product));
    }
}
