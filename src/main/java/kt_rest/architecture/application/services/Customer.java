package kt_rest.architecture.application.services;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;

@XmlRootElement(name = "Customer")
@ApiModel
public class Customer implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  

  private Long id;
  private String firstName;
  private String lastName;
  private String fullName;

  public Customer() {
    
  }
      
  public Customer(Long id, String firstName, String lastName) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      setFullName(firstName, lastName);
  }

  @XmlElement
  public Long getId() {
      return id;
  }
  
//  @ApiModelProperty(position = 1, required = true, value = "customer id. Use 0 to create a new customer")
  public void setId(Long id) {
    this.id = id;
  }

  @XmlElement
  public String getFistName() {
      return firstName;
  }
  
//  @ApiModelProperty(position = 2, required = true, value = "customer name containing only lowercase letters or numbers")
  public void setFistName(String firstName) {
    this.firstName = firstName;
    setFullName(firstName, lastName);
    System.out.println("["+id+"]"+ fullName);
  }
  
  @XmlElement
  public String getLastName() {
    return lastName;
  }

//  @ApiModelProperty(position = 3, required = false, value = "customer last name containing only lowercase letters or numbers")
  public void setLastName(String lastName) {
    this.lastName = lastName;
    setFullName(firstName, lastName);
    System.out.println("["+id+"]"+ fullName);
  }
  
  private String getFullName() {
    return firstName + " " + lastName;
  }
  
  private void setFullName(String firstName, String lastName) {
    this.fullName = firstName + " " + lastName;
    System.out.println("["+id+"]"+ fullName);
  }
}
