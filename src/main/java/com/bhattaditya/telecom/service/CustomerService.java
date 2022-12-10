package com.bhattaditya.telecom.service;

import com.bhattaditya.telecom.dto.CustomerDto;
import com.bhattaditya.telecom.entity.Customer;
import com.bhattaditya.telecom.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public CustomerDto createCustomer(Customer customer) {

        Customer newCustomer = customerRepository.saveAndFlush(customer);
        return modelMapper.map(newCustomer, CustomerDto.class);
    }

    public List<CustomerDto> fetchCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Customer updateCustomer(String customerId, Customer customer) {
        Customer updatedCustomer = findCustomer(customerId);

        updatedCustomer.setName(customer.getName());
        updatedCustomer.setEmail(customer.getEmail());

        return updatedCustomer;
    }

    private Customer findCustomer(String customerId) {
        return customerRepository
                .findById(Integer.valueOf(customerId))
                .orElseThrow(() -> new IllegalStateException("Customer with ID:" + customerId + " not found"));
    }

    public void deleteCustomer(String customerId) {
        Customer customer = findCustomer(customerId);
        customerRepository.delete(customer);
    }
}
