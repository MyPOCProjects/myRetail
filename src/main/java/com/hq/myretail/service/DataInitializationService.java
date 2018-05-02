
package com.hq.myretail.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq.myretail.entity.Product;
import com.hq.myretail.repository.ProductRepository;

@Service
public class DataInitializationService {

    @Autowired
    ProductRepository productRepository;

    private static final String VALUE = "value";
    private static final String CURRENCY_CODE = "currency_code";

    /**
     * Default constructor
     */
    public DataInitializationService() {
        // This is a default constructor
    }

    @PostConstruct
    public void initialize() {
        loadInitialData();
    }

    /**
     *
     */
    private void loadInitialData() {
        if (productRepository != null) {
            final List<Product> products = new ArrayList<>();

            // Adding products to the DB
            final Map<String, String> currentPrice1 = new HashMap<>();
            currentPrice1.put(VALUE, "13.49");
            currentPrice1.put(CURRENCY_CODE, "USD");
            final Product product1 = new Product("13860428", currentPrice1);
            products.add(product1);

            final Map<String, String> currentPrice2 = new HashMap<>();
            currentPrice2.put(VALUE, "20.49");
            currentPrice2.put(CURRENCY_CODE, "USD");
            final Product product2 = new Product("15117729", currentPrice2);
            products.add(product2);

            final Map<String, String> currentPrice3 = new HashMap<>();
            currentPrice3.put(VALUE, "145.99");
            currentPrice3.put(CURRENCY_CODE, "USD");
            final Product product3 = new Product("16483589", currentPrice3);
            products.add(product3);

            // Delete all existing data
            productRepository.deleteAll();

            // Insert the new data
            productRepository.save(products);
        }
    }

}
