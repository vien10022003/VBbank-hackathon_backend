package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Customer;
import com.vbbankhackathon.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(originPatterns = "*")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setDateOfBirth(customerDetails.getDateOfBirth());
            customer.setAddress(customerDetails.getAddress());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setEmail(customerDetails.getEmail());
            customer.setSsn(customerDetails.getSsn());
            customer.setRegistrationDate(customerDetails.getRegistrationDate());
            customer.setStatus(customerDetails.getStatus());
            customer.setGender(customerDetails.getGender());
            customer.setMaritalStatus(customerDetails.getMaritalStatus());
            customer.setOccupation(customerDetails.getOccupation());
            customer.setIncomeLevel(customerDetails.getIncomeLevel());
            customer.setEducationLevel(customerDetails.getEducationLevel());
            customer.setNationality(customerDetails.getNationality());
            customer.setPreferredLanguage(customerDetails.getPreferredLanguage());
            
            Customer updatedCustomer = customerRepository.save(customer);
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
