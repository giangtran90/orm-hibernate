package com.orm.controller;

import com.orm.model.Customer;
import com.orm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("customers")
    public String findAll(){
        List<Customer> customerList = customerService.findAll();
        return "";
    }
}
