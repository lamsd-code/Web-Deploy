package com.example.demo.repository;


import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCodeAndCustomer(String code, Customer customer);
}
