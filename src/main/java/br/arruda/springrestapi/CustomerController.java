package br.arruda.springrestapi;

import br.arruda.springrestapi.model.Customer;
import br.arruda.springrestapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable(value = "id") Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found" + customerId));
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Integer customerId,
                                                   @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        customer.setName(customerDetails.getName());
        customer.setAddress(customerDetails.getAddress());
        customer.setCpf(customerDetails.getCpf());
        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "id") Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found" + customerId));
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }
}