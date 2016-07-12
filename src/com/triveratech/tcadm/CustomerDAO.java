package com.triveratech.tcadm;

import java.util.List;

public interface CustomerDAO {

	Customer insert(Customer customer);

	Customer update(Customer customer);

	Customer delete(Customer customer);

	Customer findById(Long customerId);

	List<Customer> findAll();

	List<Customer> findByLastName(String lastName);

	List<Customer> findByFirstNameLastName(String firstName, String lastName);

}