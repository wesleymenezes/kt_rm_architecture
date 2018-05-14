package kt_rest.architecture.application.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@WebService
//(serviceName = "CustomerService", portName = "CustomerPort",
//targetNamespace = "http://service.ws.sample/",
//endpointInterface = "kt_rest.architecture.application.services.CustomerService")
public interface CustomerWS {


  @WebMethod
  @Produces("text/xml")
  public Customer createCustomer(@WebParam(name = "incustomer", targetNamespace = "http://services.application.architecture.kt_rest/") Customer incustomer);
  
  @WebMethod
  @Produces("text/xml")
  public Customer[] getCustomers();
  
//  @WebMethod//(action = "urn:customers")
//  @Produces("text/xml")
//  public Customer getCustomer(
//      @WebParam(name = "infirstname", targetNamespace = "http://services.application.architecture.kt_rest/") String infirstname,
//      @WebParam(name = "inlastname", targetNamespace = "http://services.application.architecture.kt_rest/") String inlastname
//      );

//  @WebMethod//(action = "urn:ping")
//  public String ping();

}