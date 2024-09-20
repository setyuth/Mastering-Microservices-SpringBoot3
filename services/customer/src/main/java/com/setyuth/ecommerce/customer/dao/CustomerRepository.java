package com.setyuth.ecommerce.customer.dao;

import com.setyuth.ecommerce.customer.dto.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
