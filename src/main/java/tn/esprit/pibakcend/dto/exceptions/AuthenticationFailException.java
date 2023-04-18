package tn.esprit.pibakcend.dto.exceptions;
package com.webtutsplus.ecommerce.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
