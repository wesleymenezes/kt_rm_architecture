package kt_rest.architecture.application.services;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerMap {
  
  private Map<Long, Customer> customers = new ConcurrentHashMap<Long,Customer>();
  
  public void addCustomer (Customer customer) {
    this.customers.putIfAbsent(customer.getId(), customer);
  }

  public Collection<Customer> getCustomers() {
    return this.customers.values();
  }

  public Customer getCustomers(Long id) {
    return this.customers.get(id);
  }
}
