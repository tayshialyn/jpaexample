package com.sctp.fsd.jpa.service;

import com.sctp.fsd.jpa.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {

    // TODO
    //  method signatures to work on Customer data

    public abstract Optional<Customer> createCustomer(Customer customer);

    public abstract List<Customer> getCustomers();

    public abstract Optional<Customer> updateCustomer(Integer id, Customer customer);

    public abstract boolean removeCustomer(Integer id);

    public abstract Optional<Customer> getCustomer(Integer id);

    public abstract long countCustomers();
}
