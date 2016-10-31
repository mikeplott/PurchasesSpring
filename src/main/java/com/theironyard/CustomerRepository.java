package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by michaelplott on 10/24/16.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findAll();
}
