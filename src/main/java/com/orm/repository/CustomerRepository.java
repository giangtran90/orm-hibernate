package com.orm.repository;

import com.orm.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
}
