package kt_rest.architecture.application.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.jws.WebParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import kt_rest.architecture.application.Application;
import kt_rest.architecture.application.SpringUtils;

public class CustomerWSImpl implements CustomerWS {
  
  private static final String template = "Ola, %s!";
  private final AtomicLong counter = new AtomicLong();
  
//  @Autowired
//  CustomerMap customerMap;
  CustomerMap customerMap = (CustomerMap)SpringUtils.ctx.getBean(CustomerMap.class);
//private Map<Long, Customer> customers = new ConcurrentHashMap<Long,Customer>();
  
  public Customer createCustomer(@WebParam(name = "incustomer", targetNamespace = "http://services.application.architecture.kt_rest/") Customer incustomer) {
    Customer customer;
    
    //new customer
    if (incustomer.getId()==0) {
      customer = new Customer(counter.incrementAndGet(), incustomer.getFistName(), incustomer.getLastName());
//      customers.putIfAbsent(customer.getId(), customer);
    } else {
//      customers.put(incustomer.getId(), incustomer);
      customer = incustomer;
    }
    customerMap.addCustomer(customer);
    return customer;
  }

  public Customer[] getCustomers() {
//    Customer[] c = (Customer[]) customers.values().toArray(<Customer[]>);
    Customer c[] = new Customer[customerMap.getCustomers().size()];
    c = customerMap.getCustomers().toArray(c);
    return c;
//    return Response.status(Response.Status.ACCEPTED).entity(new Customer(counter.incrementAndGet(), String.format(template, inname))).build();
  }

//  public Customer getCustomer(
//              @WebParam(name = "infirstname", targetNamespace = "http://services.application.architecture.kt_rest/") String infirstname,
//              @WebParam(name = "inlastname", targetNamespace = "http://services.application.architecture.kt_rest/") String inlastname
//      ) {
//    return new Customer(counter.incrementAndGet(), infirstname, inlastname);
////    return Response.status(Response.Status.ACCEPTED).entity(new Customer(counter.incrementAndGet(), String.format(template, inname))).build();
//  }
  
//  public String ping () {
//    return "service is published";
//  }
  
}