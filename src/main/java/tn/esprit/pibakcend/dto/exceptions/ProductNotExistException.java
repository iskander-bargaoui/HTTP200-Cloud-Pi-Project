package tn.esprit.pibakcend.dto.exceptions;
package com.webtutsplus.ecommerce.exceptions;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
