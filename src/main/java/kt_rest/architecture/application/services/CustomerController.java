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

    private static final String template = "Ola, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    CustomerMap customerMap;
    
    @RequestMapping(path="/customers", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Returns user details", notes = "Returns a complete list of users details with a date of last modification.", response = Customer.class)
    @ApiResponse(code = 202, message = "Successful retrieval of customers details", response = Customer.class)
    public ResponseEntity<Collection<Customer>> getCustomers() {
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerMap.getCustomers());
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
        return addCustomer(customer.getId().toString(), customer.getFistName(), customer.getLastName());
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
    }


    /**
     * @param inid
     * @param infirstname
     * @param inlastname
     * @return
     */
    private ResponseEntity<Customer> addCustomer(String inid, String infirstname, String inlastname) {
      Customer customer;
      if (inid.equals("0")) {
        customer = new Customer(counter.incrementAndGet(), infirstname, inlastname);
        customerMap.addCustomer(customer);
        //201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
      } else {
        customer = customerMap.getCustomers(new Long(inid));
        //202 Accepted
        if (customer!=null) return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
        //404 Not Found
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
    }
}