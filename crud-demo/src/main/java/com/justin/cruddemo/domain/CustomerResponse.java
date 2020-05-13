package com.justin.cruddemo.domain;

import com.justin.cruddemo.entity.Customer;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class CustomerResponse {
    List<Customer> customers;

    public CustomerResponse(List<Customer> customers) {
        this.customers = customers;
    }
}
