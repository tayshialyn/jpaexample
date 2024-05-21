package com.sctp.fsd.jpa.controller;

import com.sctp.fsd.jpa.model.Customer;
import com.sctp.fsd.jpa.repository.CustomerRepository;
import com.sctp.fsd.jpa.service.CustomerService;
import com.sctp.fsd.jpa.service.CustomerServiceInterface;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceInterface customerServiceInterface;


    // TODO:
    //  Implemented
    //  Add a new customer

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addNewCustomer(@RequestBody Customer toAddCustomer)
            throws Exception {
        try {
            Optional<Customer> createdCustomer = customerServiceInterface.createCustomer(toAddCustomer);
            if (createdCustomer.isEmpty())
                throw new Exception("Unable to add customer.");
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to add customer.", HttpStatus.BAD_REQUEST);
        }
    }


//    @PostMapping(path="/add")  --- extra codes (for reference)
//    public ResponseEntity<Object> addNewCustomer(@RequestBody Customer toAddCustomer) throws  Exception{
//        try{
//            Customer customer = new Customer(
//                    toAddCustomer.getName(),
//                    toAddCustomer.getEmail(),
//                    toAddCustomer.getPhone()
//            );
//            Customer createdCustomer = customerRepository.save(customer);
//            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);

// For RequestParam
//            Customer customer = new Customer(name, email, phone);
//            customerRepository.save(customer);
//            return new ResponseEntity<>("Customer added successfully.", HttpStatus.CREATED);
//            Customer createdCustomer = customerRepository.save(customer);
//           return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);

// FOr RequestParam
// To show customer details (administrative level) instead of the above message "Customer added successfully."
//            Customer customer = new Customer(name, email, phone);
//            Customer createdCustomer = customerRepository.save(customer);
//            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>("Unable to add customer.", HttpStatus.BAD_REQUEST);
//        }
//    }

    // TODO:
    //  Implemented
    //  Retrieve all customers
    @GetMapping("/all")
    public ResponseEntity<Object> getAllCustomers() throws Exception {
        try {
            List<Customer> customers = customerServiceInterface.getCustomers();
            if (customers.isEmpty()) {
                throw new Exception("No customer(s) found.");
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //TODO:
// Implemented
// Update customer by id
    @PutMapping("{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable("id") Integer id, @RequestBody Customer customer) throws Exception {
        try {
            Optional<Customer> result = customerServiceInterface.updateCustomer(id, customer);
            if (result.isEmpty()) {
                throw new Exception("Unable to update Customer.");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // TODO
    //  Implemented
    //  Delete customer by id
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") Integer id) throws Exception {
        try {
            boolean removed = customerServiceInterface.removeCustomer(id);
            if (!removed) {
                throw new Exception("Customer not removed or not found.");
            }
           return new ResponseEntity<>("Customer deleted.", HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // TODO:
    //  Implemented
    //  Retrieve a customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Integer id) throws Exception {
        try {
            Optional<Customer> customer = customerServiceInterface.getCustomer(id);
            if (customer.isEmpty()) {
                throw new Exception("Customer not found.");
            }
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/count")
    public ResponseEntity<Object> getCustomerCount() throws Exception {
        long count = customerServiceInterface.countCustomers();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
