package com.justin.cruddemo.controller;

import com.justin.cruddemo.domain.CustomerResponse;
import com.justin.cruddemo.entity.Customer;
import com.justin.cruddemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static com.justin.cruddemo.util.ResponseUtil.created;
import static java.util.Arrays.asList;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequiredArgsConstructor
@Api(tags = "Customer", description = "Services For Customer")
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/{customerNumber}", produces = "application/json")
    @ApiOperation(value = "Get Customer",
            notes = "Get Customer. ")
    public CustomerResponse getCustomer(@PathVariable("customerNumber") Integer customerId) {
        Customer customer = customerService.getCustomer(customerId);
        List<Customer> customers = customer == null ? Collections.emptyList() : asList(customer);
        return  new CustomerResponse(customers);
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "Get All Customer",
            notes = "Get All Customer. ")
    public CustomerResponse getAllCustomer() {
        List<Customer> customers = customerService.getAllCustomer();
        return  new CustomerResponse(customers);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Create a new Customer ",
            notes = "Creates a new customer")
    ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customerRequest, Errors errors, UriComponentsBuilder ucBuilder) {

        if (errors.hasErrors()) {
            return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        int customerId = customerService.createCustomer(customerRequest);
        return created("customer/",customerId,ucBuilder);
    }

    @RequestMapping(value = "/", method = PUT, produces = "application/json")
    @ApiOperation(value = "Update an existing customer",
            notes = "Updates existing customer . ")
    ResponseEntity<Void> updatecustomer(@Valid @RequestBody Customer customerRequest, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        customerService.updateCustomer(customerRequest.getCustomerNumber(), customerRequest);
        return noContent().build();
    }

    @RequestMapping(value = "/{customerNumber}", method = RequestMethod.DELETE, produces = "application/json" )
    ResponseEntity<Void> deletecustomer(@PathVariable("customerNumber") Integer customerId) {
        customerService.deleteCustomer(customerId);
        return noContent().build();
    }

}

