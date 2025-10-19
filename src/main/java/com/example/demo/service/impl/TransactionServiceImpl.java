package com.example.demo.service.impl;

import com.example.demo.converter.TransactionConverter;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;
import com.example.demo.model.dto.TransactionDTO;
import com.example.demo.model.request.TransactionCreateRequest;
import com.example.demo.model.response.ResponseDTO;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public ResponseDTO save(TransactionCreateRequest transactionCreateRequest, Long staffId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            transactionRepository.save(transactionConverter.toTransactionEntity(transactionCreateRequest, staffId));
            if(transactionCreateRequest.getId() == null){
                responseDTO.setMessage("Thêm giao dịch thành công");
            }
            else responseDTO.setMessage("Cập nhật giao dịch thành công");
        }catch (Exception e){
            System.out.println(e);
        }
        return responseDTO;
    }

    @Override
    public List<TransactionDTO> findAllByCodeAndCustomer(String code, Long id) {
        Customer customer = customerRepository.findById(id).get();
        List<Transaction> transactionEntities = transactionRepository.findAllByCodeAndCustomer(code, customer);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for(Transaction t : transactionEntities){
            transactionDTOS.add(transactionConverter.toTransactionDTO(t));
        }
        return transactionDTOS;
    }

    @Override
    public ResponseDTO findById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        Transaction transactionEntity = transactionRepository.findById(id).get();
        responseDTO.setMessage("success");
        responseDTO.setData(transactionEntity.getNote());
        return responseDTO;
    }
}

