
package com.hq.myretail.helper;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.hq.myretail.entity.Product;
import com.hq.myretail.vo.ProductVO;

@RunWith(SpringRunner.class)
public class ProductHelperTest {

    @InjectMocks
    ProductHelper productHelper;

    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convertToProductVOObjectTest() {
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

        final ProductVO actualProductVO = productHelper.convertToProductVOObject(product, productTitle);

        assertEquals(productVO.getName(), actualProductVO.getName());

    }

}
