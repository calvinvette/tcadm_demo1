package com.triveratech.tcadm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceUnit;

public class CustomerJPADAO implements CustomerDAO {
	@PersistenceContext
	private EntityManager entityManager;
	// @PersistenceUnit
	private static EntityManagerFactory entityManagerFactory;

	public EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("tcadm_demo1");
		}
		return entityManagerFactory;
	}
	
	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		CustomerJPADAO.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * @see com.triveratech.tcadm.CustomerDAO#insert(com.triveratech.tcadm.Customer)
	 */
	@Override
	public Customer insert(Customer customer) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(customer); // NPE Here unless
												// DependencyInjection is
												// working
		getEntityManager().getTransaction().commit();
		return customer;
	}

	/**
	 * @see com.triveratech.tcadm.CustomerDAO#update(com.triveratech.tcadm.Customer)
	 */
	@Override
	public Customer update(Customer customer) {
		return getEntityManager().merge(customer);
	}

	/**
	 * @see com.triveratech.tcadm.CustomerDAO#delete(com.triveratech.tcadm.Customer)
	 */
	@Override
	public Customer delete(Customer customer) {
		getEntityManager().remove(customer);
		return customer;
	}

	/**
	 * @see com.triveratech.tcadm.CustomerDAO#findById(java.lang.Long)
	 */
	@Override
	public Customer findById(Long customerId) {
		return getEntityManager().find(Customer.class, customerId);
	}

	/**
	 * @see com.triveratech.tcadm.CustomerDAO#findAll()
	 */
	@Override
	public List<Customer> findAll() {
		return getEntityManager().createQuery("select c from Customer c", Customer.class) // Asking
																							// for
																							// SQL-Injection
				.getResultList();
	}

	/**
	 * @see com.triveratech.tcadm.CustomerDAO#findByLastName(java.lang.String)
	 */
	@Override
	public List<Customer> findByLastName(String lastName) {
		return getEntityManager()
				.createQuery("select c from Customer c where c.lastName like :lastName", Customer.class) // Asking
																											// for
																											// SQL-Injection
				.setParameter("lastName", lastName).getResultList();
	}

	/**
	 * @see com.triveratech.tcadm.CustomerDAO#findByFirstNameLastName(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List<Customer> findByFirstNameLastName(String firstName, String lastName) {
		return getEntityManager()
				.createQuery("select c from Customer c where c.lastName like :lastName and c.firstName like :firstName",
						Customer.class) // Asking for SQL-Injection
				.setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
	}

}
