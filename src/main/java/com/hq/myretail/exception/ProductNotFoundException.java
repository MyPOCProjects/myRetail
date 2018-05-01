/*
//***********************************************
// Copyright UNITEDHEALTH GROUP CORPORATION 2018.
// This software and documentation contain confidential and
// proprietary information owned by UnitedHealth Group Corporation.
// Unauthorized use and distribution are prohibited.
//***********************************************
*/

package com.hq.myretail.exception;

/**
 */
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4220806516580330444L;

    public ProductNotFoundException() {
        super("No product found with given Id");
    }
}
