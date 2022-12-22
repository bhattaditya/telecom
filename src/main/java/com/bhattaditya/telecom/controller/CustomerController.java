package com.bhattaditya.telecom.controller;

import com.bhattaditya.telecom.dto.response.ApiResponse;
import com.bhattaditya.telecom.dto.request.CustomerRequestDto;
import com.bhattaditya.telecom.dto.request.CustomerEmailDto;
import com.bhattaditya.telecom.dto.response.CustomerResponseDto;
import com.bhattaditya.telecom.service.CustomerService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto newCustomer = customerService.createCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @GetMapping
    @ApiOperation("Returns list of customers")
    public ResponseEntity<List<CustomerResponseDto>> fetchCustomers() {
        List<CustomerResponseDto> customerResponseDtos = customerService.fetchCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(customerResponseDtos);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable String customerId,
                                                      @RequestBody CustomerEmailDto customerEmailDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomer(customerId,customerEmailDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedCustomer);
    }

    @DeleteMapping(value = "/{customerId}")
    public ApiResponse deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return new ApiResponse("Deleted", true);
    }
}
