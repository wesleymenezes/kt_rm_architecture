package kt_rest.architecture.application.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@WebService
public interface CustomerWS {


  @WebMethod
  @Produces("text/xml")
  public Customer createCustomer(@WebParam(name = "incustomer", targetNamespace = "http://services.application.architecture.kt_rest/") Customer incustomer);
  
  @WebMethod
  @Produces("text/xml")
  public Customer[] getCustomers();
}