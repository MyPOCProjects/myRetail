
package com.hq.myretail.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductVO {
    @JsonProperty(value = "id")
    public String productId;

    @JsonProperty(value = "name")
    public String name;

    @JsonProperty(value = "current_price")
    public Map<String, String> currentPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Map<String, String> currentPrice) {
        this.currentPrice = currentPrice;
    }

}
