package com.sctp.fsd.jpa.service;

import com.sctp.fsd.jpa.model.Customer;
import com.sctp.fsd.jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {     // implements the interface

    @Autowired
    CustomerRepository customerRepository;

    // Constructor is a good practice but optional since it's already @Autowired
//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    public Optional<Customer> createCustomer(Customer customer) {
        try {
            // try to insert a customer
            Customer newCustomer = customerRepository.save(customer);
            return Optional.of(newCustomer);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> updateCustomer(Integer id, Customer customer) {
        try {
            // find the customer to update by id
            Optional<Customer> toUpdate = customerRepository.findById(id);
            if (toUpdate.isEmpty()) {
                return Optional.empty();
            }
            Customer updateCustomer = toUpdate.get();
            updateCustomer.setName(customer.getName());
            updateCustomer.setEmail(customer.getEmail());
            updateCustomer.setPhone(customer.getPhone());

            // try to update the customer
            Customer updatedCustomer = customerRepository.save(updateCustomer);
            return Optional.of(updatedCustomer);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean removeCustomer(Integer id) {
        try {
            // find customer to delete by id
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isEmpty()) {
                return false;
            }
            customerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Customer> getCustomer(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public long countCustomers() {
        long result = customerRepository.count();
        return result;
    }
}
