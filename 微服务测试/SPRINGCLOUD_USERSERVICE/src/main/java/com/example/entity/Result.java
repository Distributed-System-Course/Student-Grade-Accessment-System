package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.List;

@Data
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private List data;
}
