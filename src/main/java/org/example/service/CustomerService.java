package org.example.service;
import org.example.entity.Customer;
import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomers();
}