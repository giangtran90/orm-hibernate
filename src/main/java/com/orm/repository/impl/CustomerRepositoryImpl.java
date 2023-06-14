package com.orm.repository.impl;

import com.orm.model.Customer;
import com.orm.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    // tiem EntityManager vao trong Repository
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        // luu thong tin ta dung methode persist
        entityManager.persist(customer);
    }
}
