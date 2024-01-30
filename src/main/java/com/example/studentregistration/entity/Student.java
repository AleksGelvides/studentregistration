package com.example.studentregistration.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Student {
    private UUID id;
    private String name;
    private String lastName;
    private int age;
}
