package com.bhattaditya.telecom.controller;

import com.bhattaditya.telecom.dto.CustomerDto;
import com.bhattaditya.telecom.entity.Customer;
import com.bhattaditya.telecom.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> fetchCustomers() {
        List<CustomerDto> customerDtos = customerService.fetchCustomers();

        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customerId,customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
