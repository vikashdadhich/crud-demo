package com.justin.cruddemo.service;

import com.justin.cruddemo.entity.Customer;
import com.justin.cruddemo.exceptions.RecordNotFoundException;
import com.justin.cruddemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {


@Autowired
CustomerRepository customerRepository;
    public Integer createCustomer(Customer customer) {
        Customer entity = customerRepository.save(customer);
        return entity.getCustomerNumber();
    }

    public Customer getCustomer(Integer customerNumber) {
        Optional<Customer> customer = customerRepository.findById(customerNumber);
        if (customer.isPresent())
            return customer.get();
        else {
           return null;
    }
    }


    public List<Customer> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    public Customer updateCustomer(Integer customerId, Customer newCustomer){
        Customer existingCustomer = getCustomer(customerId);

        BeanUtils.copyProperties(newCustomer,
                existingCustomer);
      return  customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Integer customerId){
        Optional<Customer> employee = customerRepository.findById(customerId);
        if(employee.isPresent()) {
            customerRepository.deleteById(customerId);
        }
    }
}
