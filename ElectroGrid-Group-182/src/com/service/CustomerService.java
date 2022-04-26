package com.service;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

//For JSON
import com.google.gson.*;
import com.model.Customer;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customer")
public class CustomerService
{
 Customer customerObj = new Customer();

              //Insert Customer details
 
              @POST
              @Path("/")
              @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
              @Produces(MediaType.TEXT_PLAIN)
              public String insertCustomer(@FormParam("customerName") String customerName,
               @FormParam("customerAddress") String customerAddress,
               @FormParam("customerEmail") String customerEmail,
               @FormParam("customerContact") String customerContact)
              {
               String output = customerObj.insertCustomer(customerName, customerAddress , customerEmail , customerContact);
              return output;
              }

              //Read Customer Details
 
              @GET
              @Path("/")
              @Produces(MediaType.TEXT_HTML)
              public String readCustomers()
               {
               return customerObj.readCustomers();
              }
 
              
              //Update Customer Details
 
              @PUT
              @Path("/")
              @Consumes(MediaType.APPLICATION_JSON)
              @Produces(MediaType.TEXT_PLAIN)

              public String updateCustomer(String customerData)
              {
              //Convert the input string to a JSON object
               JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
               
              //Read the values from the JSON object
               String customerID = customerObject.get("customerID").getAsString();
               String  customerName = customerObject.get("customerName").getAsString();
               String customerAddress = customerObject.get("customerAddress").getAsString();
               String customerEmail = customerObject.get("customerEmail").getAsString();
               String customerContact = customerObject.get("customerContact").getAsString();
               String output = customerObj.updateCustomer(customerID, customerName, customerAddress, customerEmail, customerContact);
              return output;
              }
 
 
              //Delete Customer Details
 
              @DELETE
              @Path("/")
              @Consumes(MediaType.APPLICATION_XML)
              @Produces(MediaType.TEXT_PLAIN)
              public String deleteCustomer(String CustomerData)
              {
               
              //Convert the input string to an XML document
               Document doc = Jsoup.parse(CustomerData, "", Parser.xmlParser());

              //Read the value from the element <itemID>
               String customerID = doc.select("customerID").text();
               String output = customerObj.deleteCustomer(customerID);
              return output;
              }

              //Search Customer Details
 
              @GET
              @Path("/profile/{customerID}")
              @Consumes(MediaType.APPLICATION_JSON)
              @Produces(MediaType.TEXT_PLAIN)
              //@Produces(MediaType.TEXT_HTML)
              public String readprofile(@PathParam("customerID") String customerID) {



              return customerObj.viewProfile(customerID);


              }




              }
