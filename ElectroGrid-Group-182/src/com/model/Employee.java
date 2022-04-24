package com.model;
import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import com.util.DBConnect;
public class Employee {
	
	private static Connection con=null;
	
	//Insert Item
	public String insertEmployeeDetails(String employeeName, String employeeEmail, String empAge, String password, String phone, String nic, String gender, String address)
	{
		String output = "";
		
		try
		{
			con = DBConnect.connect();
   		   
		   if (con == null)
           {return "Error while connecting to the database for inserting."; }
		   
			 // create a prepared statement
			 String query = " insert into employee (`employeeNumber`,`employeeName`,`employeeEmail`,`empAge`,`password`,`phone`,`nic`, `gender`, `address`)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, employeeName);
			preparedStmt.setString(3, employeeEmail);
			preparedStmt.setInt(4, Integer.parseInt(empAge));
			preparedStmt.setString(5, password);
			preparedStmt.setInt(6, Integer.parseInt(phone));
			preparedStmt.setString(7, nic);
			preparedStmt.setString(8, gender);
			preparedStmt.setString(9, address);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		 catch (Exception e)
		 {
			 output = "Error while inserting the item.";
			 System.err.println(e.getMessage());
		 }
		    return output;
	}
	
	
	//Read Item
		public String readEmployee()
	    {
		   String output = "";
		   try
		   {
			   con = DBConnect.connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
		     // Prepare the html table to be displayed
		     output = "<table border='1'><tr><th>Employee No</th><th>employee Name</th>" +
					   "<th>EmployeeEmail</th>" +
					   "<th>Emp Age</th>" +
					  
					   "<th>password</th>" +
					   "<th>phone no</th>" +
					   "<th>Nic no</th>" +
					   "<th>Gender</th>" +
					   "<th>address</th>" +
					   "<th>Update</th><th>Remove</th></tr>";
		     

			 String query = "select * from employee";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String employeeNumber = Integer.toString(rs.getInt("employeeNumber"));//see
				 String employeeName = rs.getString("employeeName");
				 String employeeEmail = rs.getString("employeeEmail");
				 String empAge = rs.getString("empAge");
				 String password = rs.getString("password");
				 String phone = Integer.toString(rs.getInt("phone"));
				 String nic = rs.getString("nic");
				 String gender = rs.getString("gender");
				 String address = rs.getString("address");
				
				 // Add into the html table
				 output += "<tr><td>" + employeeNumber + "</td>";
				 output += "<td>" + employeeName + "</td>";
				 output += "<td>" + employeeEmail + "</td>";
				 output += "<td>" + empAge + "</td>";
				 output += "<td>" + password + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + nic + "</td>";
				 output += "<td>" + gender + "</td>";
				 output += "<td>" + address + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action=''>"
							 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='employeeNumber' type='hidden' value='" + employeeNumber
							 + "'>" + "</form></td></tr>";
			 }
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		   }
		   catch (Exception e)
		   {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		
		//update
		public String updateEmployee(String employeeNumber, String employeeName, String employeeEmail, String empAge, String password, String phone, String nic, String gender, String address)
		 
		 {
		 String output = "";
		 try
		 {
			 con = DBConnect.connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE employee SET employeeName=?,employeeEmail=?,empAge=?,password=?,phone=?,nic=?,gender=?,address=?  WHERE employeeNumber=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, employeeName);
		 preparedStmt.setString(2, employeeEmail);
		 preparedStmt.setDouble(3, Integer.parseInt(empAge));
		 preparedStmt.setString(4, password);
		 preparedStmt.setInt(5, Integer.parseInt(phone));
		 preparedStmt.setString(6, nic);
		 preparedStmt.setString(7, gender);
		 preparedStmt.setString(8, address);
		 preparedStmt.setInt(9, Integer.parseInt(employeeNumber));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//delete
		public String deleteEmployee(String employeeNumber)
		 {
		 String output = "";
		 try
		 {
			 con = DBConnect.connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from employee where employeeNumber=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(employeeNumber));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		

		// view profile details
					
					public String viewProfile(String employeeNumber) {


						String output = "";

						try {
							con = DBConnect.connect();

							if (con == null) {
								return "Error while connecting to the database for reading.";
							}

							 // Prepare the html table to be displayed
						     output = "<table border='1'><tr><th>Employee No</th><th>employee Name</th>" +
									   "<th>EmployeeEmail</th>" +
									   "<th>Emp Age</th>" +
									  
									   "<th>password</th>" +
									   "<th>phone no</th>" +
									   "<th>Nic no</th>" +
									   "<th>Gender</th>" +
									   "<th>address</th>" +
									   "<th>Update</th><th>Remove</th></tr>";

							String query = "select *  from employee where employeeNumber=' " + employeeNumber + "'" ;


							Statement stmt = con.createStatement();


							ResultSet rs = stmt.executeQuery(query);

							while (rs.next()) {
								 String employeeNumber1 = Integer.toString(rs.getInt("employeeNumber"));//see
								 String employeeName = rs.getString("employeeName");
								 String employeeEmail1 = rs.getString("employeeEmail");
								 String empAge = rs.getString("empAge");
								 String password1 = rs.getString("password");
								 String phone = Integer.toString(rs.getInt("phone"));
								 String nic = rs.getString("nic");
								 String gender = rs.getString("gender");
								 String address = rs.getString("address");



								output += "<tr><td>" + employeeNumber1 + "</td>";
								output += "<td>" + employeeName + "</td>";
								output += "<td>" + employeeEmail1 + "</td>";
								output += "<td>" + empAge + "</td>";
								output += "<td>" + password1 + "</td>";
								output += "<td>" + phone + "</td>";
								output += "<td>" + nic + "</td>";
								output += "<td>" + gender + "</td>";
								output += "<td>" + address + "</td>";



								 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
										 + "<td><form method='post' action=''>"
										 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
										 + "<input name='employeeNumber' type='hidden' value='" + employeeNumber
										 + "'>" + "</form></td></tr>";
						 
							}

							con.close();

							output += "</table>";


						} catch (Exception e) 
						{

							output = "Error while Viewing the user profile.";
							System.err.println(e.getMessage());
						}

						return output;
					}


				}


				
				

