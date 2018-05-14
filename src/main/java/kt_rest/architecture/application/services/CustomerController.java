package kt_rest.architecture.application.services;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import kt_rest.architecture.application.Application;


@RestController
@CrossOrigin
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    CustomerMap customerMap;
    
    @RequestMapping(path="/customers", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Returns customer details", notes = "Returns a complete list of customer details.", response = Customer.class)
    @ApiResponse(code = 200, message = "Successful retrieval of customers details", response = Customer.class)
    public ResponseEntity<Collection<Customer>> getCustomers() {
      return ResponseEntity.status(HttpStatus.OK).body(customerMap.getCustomers());
    }
    
//    @RequestMapping(path="/customers", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity<Customer> createCustomers(
//        @RequestParam(value="inid", defaultValue="0") String inid,
//        @RequestParam(value="infirstname", defaultValue="First Name Missing") String infirstname,
//        @RequestParam(value="inlastname", defaultValue="Last Name Missing") String inlastname) {
//      Customer customer = null;
//      return addCustomer(inid, infirstname, inlastname);
//    }

    @RequestMapping(path="/customers", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Customer> createCustomers(@RequestBody Customer customer) {
      if (customer != null) {
        return addCustomer(customer);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
    }


    /**
     * @param customer
     * @return customer
     */
    private ResponseEntity<Customer> addCustomer(Customer customer) {

      if (customer.getId()==0) {
        customer = new Customer(customerMap.getCustomers().size()+1, customer.getFistName(), customer.getLastName());
        customerMap.addCustomer(customer);
        //201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
      } else {
//        customer = customers.get(new Long(inid));
        Customer customerCheck = customerMap.getCustomers(customer.getId());
        if (customerCheck != null) {
          customerMap.addCustomer(customer);
        //202 Accepted
          return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);}
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customer);
        
      }
    }
}