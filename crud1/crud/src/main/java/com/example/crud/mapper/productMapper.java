package com.example.crud.mapper;

import com.example.crud.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productMapper {
    Product findall();

    List<Product> plist();

    Product findProductById(int pid);
}
