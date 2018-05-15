package kt_rest.architecture.application.services;

import javax.jws.WebParam;

import kt_rest.architecture.application.SpringUtils;

public class CustomerWSImpl implements CustomerWS {
  
  //This class is out of Spring Context, so the singleton will be directly retrieve from Spring
  CustomerMap customerMap = (CustomerMap)SpringUtils.ctx.getBean(CustomerMap.class);
  
  public Customer createCustomer(@WebParam(name = "incustomer", targetNamespace = "http://services.application.architecture.kt_rest/") Customer incustomer) {
    Customer customer = null;
    
    //new customer
    if (incustomer.getId()==0) {
      customer = new Customer(customerMap.getCustomers().size()+1, incustomer.getFistName(), incustomer.getLastName());
      customerMap.addCustomer(customer);
    } else {
      Customer customerCheck = customerMap.getCustomers(incustomer.getId());
      if (customerCheck != null) {
        customerMap.addCustomer(incustomer);
        customer = incustomer;
      }
      
    }
    
    return customer;
  }

  public Customer[] getCustomers() {
    Customer c[] = new Customer[customerMap.getCustomers().size()];
    c = customerMap.getCustomers().toArray(c);
    return c;
  }
}