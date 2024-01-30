package com.example.systemmanage.repository;

import com.example.systemmanage.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
