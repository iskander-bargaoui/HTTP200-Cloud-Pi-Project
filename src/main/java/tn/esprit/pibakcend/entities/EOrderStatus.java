package tn.esprit.pibakcend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EOrderStatus {

        CREATED("Order created"),

        WAITING("Order placed, awaiting payment confirmation"),

        PAID("Your order has been paid"),

        SHIPPED("Your order has been sent"),

        ON_THE_WAY("Your order is on the way"),

        CANCELED("Your order has been canceled"),

        DELIVERED("Your order has been delivered");

        private final String description;
    }


