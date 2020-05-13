package com.justin.cruddemo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="customers")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    private Integer customerNumber;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
