
package com.hq.myretail.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hq.myretail.exception.ProductNotFoundException;
import com.hq.myretail.service.ProductService;
import com.hq.myretail.vo.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductDetailsTest() throws Exception {
        final ProductVO productVO = new ProductVO();
        productVO.setProductId("13860428");
        productVO.setName("The Big Lebowski (Blu-ray)");
        final Map<String, String> currentPrice = new HashMap<>();
        currentPrice.put("value", "13.49");
        currentPrice.put("currency_code", "USD");
        productVO.setCurrentPrice(currentPrice);

        Mockito.when(productService.getProductDetailsById(Mockito.anyString())).thenReturn(productVO);
        final String product_url = "/products/13860428";
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(product_url).accept(
                MediaType.APPLICATION_JSON_VALUE);

        final MvcResult actual = mockMvc.perform(requestBuilder).andReturn();

        final String expected = "{\"id\":\"13860428\",\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\": \"13.49\",\"currency_code\":\"USD\"}}";

        JSONAssert.assertEquals(expected, actual.getResponse().getContentAsString(), false);

    }

    @Test
    public void getProductDetailsTest_wrongProductId() throws Exception {
        Mockito.when(productService.getProductDetailsById(Mockito.anyString())).thenThrow(new NullPointerException());

        try {
            final String url = "/products/9999999";
            final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(
                    MediaType.APPLICATION_JSON_VALUE);
            mockMvc.perform(requestBuilder).andReturn();
        } catch (final ProductNotFoundException e) {
            logger.debug("Product not found exception test success");
        }
    }

    @Test
    public void updateProductDetailsTest() throws Exception {
        final String input = "{\"id\":\"13860428\",\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\": \"13.49\",\"currency_code\":\"USD\"}}";
        final String product_url = "/products/13860428";

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(product_url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        final MvcResult actual = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.ACCEPTED.value(), actual.getResponse().getStatus());
    }

}
