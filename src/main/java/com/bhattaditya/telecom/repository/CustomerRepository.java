package com.bhattaditya.telecom.repository;

import com.bhattaditya.telecom.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
