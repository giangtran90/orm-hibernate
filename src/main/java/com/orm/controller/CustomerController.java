package com.orm.controller;

import com.orm.model.Customer;
import com.orm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    // goi toi view create chua form
    @RequestMapping("init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    // sau khi submit form create se goi den request customer/save
    // du lieu form se duoc dinding thanh 1 customer => muon su dung thanh 1 object cua ModelAndView ta su dung @ModelAttribute
    @PostMapping("save")
    public ModelAndView save(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("info");
        // goi phuong thuc den tang service
        customerService.save(customer);
        return modelAndView;
    }
}
