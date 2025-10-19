package com.example.demo.converter;


import com.example.demo.entity.Customer;
import com.example.demo.model.dto.CustomerDTO;
import com.example.demo.model.request.CustomerCreateRequest;
import com.example.demo.model.request.CustomerSearchRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;
    public CustomerDTO toCustomerDTO(Customer customerEntity){
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }
    public Customer toCustomerEntity(CustomerCreateRequest customerCreateRequest){
        Customer customerEntity = modelMapper.map(customerCreateRequest, Customer.class);
        return customerEntity;
    }
    public CustomerCreateRequest toCustomerCreateRequest(Customer customerEntity){
        CustomerCreateRequest customerCreateRequest = modelMapper.map(customerEntity, CustomerCreateRequest.class);
        return customerCreateRequest;
    }
}

