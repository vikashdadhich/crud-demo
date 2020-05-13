package com.justin.cruddemo.controller;

import com.justin.cruddemo.entity.Customer;
import com.justin.cruddemo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;
@InjectMocks
    private CustomerController customerController;
private MockMvc mvc;

@BeforeEach
    public void setup(){
          mvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
@Test
    public void getCustomers() throws Exception{
    Customer customer  = Customer.builder().customerNumber(123)
            .contactFirstName("vikash")
            .contactLastName("dadhich")
            .build();
    when(customerService.getCustomer(123)).thenReturn(customer);
    mvc.perform(get("/customer/{customerNumber}", 123))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) jsonPath("customers.length()",equalTo(1)));

}
}
