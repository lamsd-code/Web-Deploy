package com.example.demo.repository.custom;

import com.example.demo.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerRepositoryCustom {
    List<Customer> findAll(Map<String, Object> conditions);
}

