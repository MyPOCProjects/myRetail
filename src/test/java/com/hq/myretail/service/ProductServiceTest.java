
package com.hq.myretail.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hq.myretail.entity.Product;
import com.hq.myretail.helper.ProductHelper;
import com.hq.myretail.remotehttp.InteractHttpClient;
import com.hq.myretail.repository.ProductRepository;
import com.hq.myretail.vo.ProductVO;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductHelper productHelper;

    @Mock
    InteractHttpClient interactHttpClient;

    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductDetailsByIdTest() throws JsonParseException, JsonMappingException, IOException {

        final String productTitle = "The Big Lebowski (Blu-ray)";
        final ProductVO productVO = new ProductVO();
        productVO.setProductId("13860428");
        productVO.setName(productTitle);
        final Map<String, String> currentPrice = new HashMap<>();
        currentPrice.put("value", "13.49");
        currentPrice.put("currency_code", "USD");
        productVO.setCurrentPrice(currentPrice);

        final Product product = new Product();
        product.setProductId("13860428");
        product.setCurrentPrice(currentPrice);

        Mockito.when(productRepository.findProductByProductId(anyString())).thenReturn(product);
        Mockito.when(productHelper.convertToProductVOObject(anyObject(), anyString())).thenReturn(productVO);

        // Actual result
        final ProductVO actualProductVO = productService.getProductDetailsById("13860428");

        assertEquals(productVO, actualProductVO);

    }

    @Test
    public void updateProductDetails() {

    }

}
