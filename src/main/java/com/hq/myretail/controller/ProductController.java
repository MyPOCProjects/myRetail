
package com.hq.myretail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hq.myretail.entity.Product;
import com.hq.myretail.exception.InvalidRequestException;
import com.hq.myretail.exception.ServiceException;
import com.hq.myretail.service.ProductService;
import com.hq.myretail.vo.ProductVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(" Get product details using productId ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductVO.class) })
    public ResponseEntity<ProductVO> getProductDetails(@PathVariable("id") String productId) {
        logger.info("Inside getProductDetails : {}", productId);

        ProductVO productVO = null;

        try {
            productVO = productService.getProductDetailsById(productId);
        } catch (final Exception e) {
            logger.error("Error while fetching product details : {}", e);
            throw new ServiceException("Error while fetching product details");
        }
        logger.info("Product details : {}", productVO);
        return new ResponseEntity<>(productVO, HttpStatus.OK);
    }

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(" Get all products details from DB ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Product.class) })
    public ResponseEntity<List<Product>> getAllProducts() {
        final List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    @ApiOperation(" Update product details ")
    @ApiResponses(value = { @ApiResponse(code = 202, message = "ACCEPTED", response = Void.class) })
    public ResponseEntity<Void> updateProductDetails(@RequestBody ProductVO productVO,
            @PathVariable("id") String productId) {
        if (!productVO.getProductId().equalsIgnoreCase(productId)) {
            throw new InvalidRequestException("ProductId does not match with input payload");
        }
        try {
            productService.updateProductDetails(productVO);
        } catch (final Exception e) {
            logger.error("Error while updating product details : {}", e);
            throw new ServiceException("Error while updating product details");
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
