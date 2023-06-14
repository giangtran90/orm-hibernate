package com.orm.controller;

import com.orm.model.Customer;
import com.orm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    // sau khi submit form create se goi den request save
    // du lieu form se duoc binding thanh 1 customer => muon su dung thanh 1 object cua ModelAndView ta su dung @ModelAttribute
    @PostMapping("save")
    public ModelAndView save(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("info");
        // goi phuong thuc den tang service
        customerService.save(customer);
        return modelAndView;
    }

    //Tham số @PathVariable int id lấy id của customer từ đường dẫn rồi gán vào biến id.
    //hàm customerService.findById(id) sẽ lấy customer theo id rồi truyền sang view edit.html
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, Customer customer){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    // sau khi submit form create se goi den request update
    @PostMapping("update")
    public ModelAndView update(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("info");
        customerService.save(customer);
        return modelAndView;
    }
    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable Long id, Customer customer){
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    // sau khi submit form create se goi den request remove
    @PostMapping("remove")
    public ModelAndView remove(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        customerService.remove(customer.getId());
        return modelAndView;
    }
}
