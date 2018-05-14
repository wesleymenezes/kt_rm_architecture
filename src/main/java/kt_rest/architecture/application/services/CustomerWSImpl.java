package kt_rest.architecture.application.services;

import java.util.concurrent.atomic.AtomicLong;

import javax.jws.WebParam;

import org.springframework.beans.factory.annotation.Autowired;

import kt_rest.architecture.application.SpringUtils;

public class CustomerWSImpl implements CustomerWS {
  
  private final AtomicLong counter = new AtomicLong();
  
  //This class is out of Spring Context, so the singleton will be directly retrieve from Spring
  CustomerMap customerMap = (CustomerMap)SpringUtils.ctx.getBean(CustomerMap.class);
  
  public Customer createCustomer(@WebParam(name = "incustomer", targetNamespace = "http://services.application.architecture.kt_rest/") Customer incustomer) {
    Customer customer;
    
    //new customer
    if (incustomer.getId()==0) {
      customer = new Customer(counter.incrementAndGet(), incustomer.getFistName(), incustomer.getLastName());
    } else {
      customer = incustomer;
    }
    customerMap.addCustomer(customer);
    return customer;
  }

  public Customer[] getCustomers() {
    Customer c[] = new Customer[customerMap.getCustomers().size()];
    c = customerMap.getCustomers().toArray(c);
    return c;
  }
}