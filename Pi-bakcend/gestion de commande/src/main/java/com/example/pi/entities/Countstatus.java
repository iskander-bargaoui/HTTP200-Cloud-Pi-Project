package com.example.pi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Countstatus {
    private Long count;
    private String status;
}
