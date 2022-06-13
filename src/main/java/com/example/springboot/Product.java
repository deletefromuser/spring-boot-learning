package com.example.springboot;

import lombok.Data;

@Data
public class Product {
	String id;
	String name;
	ProductInfo info;
}
