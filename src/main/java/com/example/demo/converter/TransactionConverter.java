package com.example.demo.converter;


import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.model.dto.TransactionDTO;
import com.example.demo.model.request.TransactionCreateRequest;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionConverter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    public Transaction toTransactionEntity(TransactionCreateRequest t, Long staffId){
        Transaction transactionEntity = modelMapper.map(t, Transaction.class);
        User userEntity = userRepository.findById(staffId).get();
        Customer customerEntity = customerRepository.findById(t.getCustomerId()).get();
        if(t.getId() != null){
            Transaction tmp = transactionRepository.findById(t.getId()).get();
            transactionEntity.setCreatedDate(tmp.getCreatedDate());
            transactionEntity.setCreatedBy(tmp.getCreatedBy());
            transactionEntity.setModifiedDate(new Date());
            transactionEntity.setModifiedBy(userEntity.getUserName());
        }
        else{
            transactionEntity.setCreatedBy(userEntity.getUserName());
        }
        transactionEntity.setCustomer(customerEntity);
        return transactionEntity;
    }
    public TransactionDTO toTransactionDTO(Transaction transactionEntity){
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        return transactionDTO;
    }
}

