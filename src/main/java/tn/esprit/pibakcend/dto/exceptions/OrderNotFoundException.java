package tn.esprit.pibakcend.dto.exceptions;
package com.webtutsplus.ecommerce.exceptions;

public class OrderNotFoundException extends IllegalArgumentException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
