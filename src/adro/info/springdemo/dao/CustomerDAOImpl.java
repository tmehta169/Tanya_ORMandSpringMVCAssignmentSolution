package adro.info.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adro.info.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		
		Query querry = session.createQuery("from Customer order by lastName",
											Customer.class);
		List<Customer> customerList = querry.getResultList();
		
		return customerList;
	}


	@Override
	public void saveCustomers(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}


	@Override
	public Customer getCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();

		Customer theCustomer = session.get(Customer.class, theId);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();

	Query query = session.createQuery("delete from Customer where id=:customerId");
	query.setParameter("customerId", theId);	
	query.executeUpdate();
	}


	@Override
	public List<Customer> searchCustomer(String theName) {
Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
											Customer.class);
		query.setParameter("theName", theName);
		List<Customer> customerList = query.getResultList();
		
		return customerList;
		
	}
}
