
package com.hq.myretail.exception;

/**
 */
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4220806516580330444L;

    public ProductNotFoundException() {
        super("No product found with given Id");
    }
}
