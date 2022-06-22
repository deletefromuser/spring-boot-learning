package com.example.springboot.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo implements Serializable {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
