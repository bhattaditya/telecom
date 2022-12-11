package com.bhattaditya.telecom.service;

import com.bhattaditya.telecom.dto.request.CustomerRequestDto;
import com.bhattaditya.telecom.dto.request.CustomerEmailDto;
import com.bhattaditya.telecom.dto.response.CustomerResponseDto;
import com.bhattaditya.telecom.entity.Customer;
import com.bhattaditya.telecom.exception.ResourceNotFoundException;
import com.bhattaditya.telecom.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        Customer newCustomer = customerRepository.saveAndFlush(customer);

        return modelMapper.map(newCustomer, CustomerResponseDto.class);
    }

    public List<CustomerResponseDto> fetchCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomerResponseDto updateCustomer(String customerId, CustomerEmailDto customerEmailDto) {
        Customer updatedCustomer = findCustomer(customerId);
        updatedCustomer.setEmail(customerEmailDto.getEmail());

        return modelMapper.map(updatedCustomer, CustomerResponseDto.class);
    }

    private Customer findCustomer(String customerId) {
        return customerRepository
                .findById(Integer.valueOf(customerId))
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", customerId));
    }

    public void deleteCustomer(String customerId) {
        Customer customer = findCustomer(customerId);
        customerRepository.delete(customer);
    }
}