package com.buinam.redisdemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    // Serializable cause Redis doesnt understand the Java Object
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private int age;
}