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
        if (findById(customer.getId()) == null){
            // luu thong tin ta dung methode persist
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
    }

    @Override
    public Customer findById(Long id) {
        if (id == null) return null;
        return entityManager.find(Customer.class, id);
    }

    @Override
    public boolean remove(Long id) {
        entityManager.remove(findById(id));
        return true;
    }
}
