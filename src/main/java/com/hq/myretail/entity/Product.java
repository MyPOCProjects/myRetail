
package com.hq.myretail.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 */
@Document(collection = "products")
public class Product {

    @Id
    public String productId;
    public Map<String, String> currentPrice;

    public Product() {

    }

    public Product(String productId, Map<String, String> currentPrice) {
        this.productId = productId;
        this.currentPrice = currentPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Map<String, String> getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @param currentPrice
     *            the currentPrice to set
     */
    public void setCurrentPrice(Map<String, String> currentPrice) {
        this.currentPrice = currentPrice;
    }

}
