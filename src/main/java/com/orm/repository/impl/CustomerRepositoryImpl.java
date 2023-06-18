package com.orm.repository.impl;

import com.orm.model.Customer;
import com.orm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    // tiem EntityManager vao trong Repository
//    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        // dung truy van dong
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        if (findById(customer.getId()) == null){
            // C1: luu thong tin ta dung methode persist
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            // test thu rollback
//            if(true){
//                entityManager.getTransaction().rollback();
//                entityManager.getTransaction().begin();
//            }
            entityManager.getTransaction().commit();
        } else {
            entityManager.merge(customer);
        }
    }

    @Override
    public Customer findById(Long id) {
        if (id == null) return null;
        return entityManager.find(Customer.class, id);
//        TypedQuery<Customer> query = entityManager.createNamedQuery("FIND_CUSTOMER_BY_ID", Customer.class).setParameter("id", id);
//        return query.getSingleResult();
    }

    @Override
    public boolean remove(Long id) {
        entityManager.remove(findById(id));
        return true;
    }
}
