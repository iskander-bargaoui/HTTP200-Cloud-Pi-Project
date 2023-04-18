package tn.esprit.pibakcend.dto.exceptions;
package com.webtutsplus.ecommerce.exceptions;

public class CustomException extends IllegalArgumentException {
    public CustomException(String msg) {
        super(msg);
    }
}
