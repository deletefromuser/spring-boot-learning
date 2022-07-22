package com.example.springboot.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Todo implements Serializable {
    private static final long serialVersionUID = 0L;
    private int userId;
    private int id;
    @NonNull
    private String title;
    private boolean completed;
}
