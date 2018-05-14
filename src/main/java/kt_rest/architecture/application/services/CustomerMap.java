package kt_rest.architecture.application.services;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Configuration
@Service("customerMap")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerMap {
  
  private Map<Integer, Customer> customers = new ConcurrentHashMap<Integer,Customer>();
  
  
  public void addCustomer (Customer customer) {
    this.customers.put(customer.getId(), customer);
  }

  public Collection<Customer> getCustomers() {
    return this.customers.values();
  }

  public Customer getCustomers(Integer id) {
    return this.customers.get(id);
  }
}
