package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Employee;

@Path("/Employee")
public class EmployeeService {
Employee empobj = new Employee();
Employee emp1 = new Employee();

	//Read Details
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML) 
		
		public String readEmployee()
		{
			return empobj.readEmployee();
		} 
	
	//Create account
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertEmployee(@FormParam("employeeName") String employeeName,
		 @FormParam("employeeEmail") String employeeEmail,
		 @FormParam("empAge") String empAge,
		 @FormParam("password") String password,
		 @FormParam("phone") String phone,
		 @FormParam("nic") String nic,
		@FormParam("gender") String gender,
         @FormParam("address") String address)
		 
		
	
		{
		 String output = empobj.insertEmployeeDetails(employeeName,
				 employeeEmail,empAge,password, phone,nic, gender, address);
		return output;
		}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateEmployee(String employeeData)
		{
		//Convert the input string to a JSON object
		 JsonObject employeeobj = new JsonParser().parse(employeeData).getAsJsonObject();
		//Read the values from the JSON object
		 String employeeNumber = employeeobj.get("employeeNumber").getAsString();
		 String employeeName = employeeobj.get("employeeName").getAsString();
		 String employeeEmail = employeeobj.get("employeeEmail").getAsString();
		 String empAge = employeeobj.get("empAge").getAsString();
		 String password = employeeobj.get("password").getAsString();
		 String phone = employeeobj.get("phone").getAsString();
		 String nic = employeeobj.get("nic").getAsString();
		 String gender = employeeobj.get("gender").getAsString();
		 String address = employeeobj.get("address").getAsString();
		 String output = empobj.updateEmployee(employeeNumber, employeeName, employeeEmail, empAge, password, phone, nic, gender, address);
		return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteEmployee(String employeeData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String employeeNumber = doc.select("employeeNumber").text();
		 String output = empobj.deleteEmployee(employeeNumber);
		return output;//output
		}
		

		// view profile details
		@GET
		@Path("/profile/{employeeNumber}") //set id to path to get one profile details 
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		//@Produces(MediaType.TEXT_HTML)
		public String readprofile(@PathParam("employeeNumber") String employeeNumber) {

			return empobj.viewProfile(employeeNumber);


		}


	

}
