package tn.esprit.pibakcend.dto.exceptions;
package com.webtutsplus.ecommerce.exceptions;

public class CartItemNotExistException extends IllegalArgumentException {
    public CartItemNotExistException(String msg) {
        super(msg);
    }
}
