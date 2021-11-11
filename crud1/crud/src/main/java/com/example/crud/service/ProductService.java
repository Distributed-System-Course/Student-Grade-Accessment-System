package com.example.crud.service;

import com.example.crud.entity.Product;
import com.example.crud.mapper.productMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    productMapper productmapper;
    public Product findall() {
        return productmapper.findall();
    }

    public List<Product> pList() {
        return  productmapper.plist();
    }

    public Product findProductById(int pid) {
        return productmapper.findProductById(pid);
    }
}
