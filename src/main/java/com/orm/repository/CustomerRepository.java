package com.orm.repository;

import com.orm.model.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CustomerRepository {
    List<Customer> findAll();
    void save(Customer customer);
}
