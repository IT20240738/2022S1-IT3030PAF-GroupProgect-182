package com.model;

import java.sql.*;
import com.util.DBConnect;
public class Customer

{ //A common method to connect to the DB
private static Connection con = null;
 
 
 /*try
 {
    Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "hotel*123");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }*/
			// Insert Customer Details

			public String insertCustomer(String name, String address, String email, String contact)
			 {
			 String output = "";
			 try
			 {
			  con = DBConnect.connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into customer (`customerID`,`customerName`,`customerAddress`,`customerEmail`,`customerContact`)"
			 + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, email);
			 preparedStmt.setString(5, contact);
			 // execute the statement

			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while inserting the customer.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }


			//Read Customer Details

			public String readCustomers()
			{
			String output = "";
			try
			{
			 con = DBConnect.connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th><th>Customer Address</th>" +
			"<th>Customer Email</th>" +
			"<th>Customer Contact</th>" +
			"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);



			// iterate through the rows in the result set
			 while (rs.next())
			 {
			 String customerID = Integer.toString(rs.getInt("customerID"));
			 String customerName = rs.getString("customerName");
			 String customerAddress = rs.getString("customerAddress");
			 String customerEmail = rs.getString("customerEmail");
			 String customerContact = rs.getString("customerContact");

			 // Add into the html table
			 output += "<tr><td>" + customerName + "</td>";
			 output += "<td>" + customerAddress + "</td>";
			 output += "<td>" + customerEmail + "</td>";
			 output += "<td>" + customerContact + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='customer.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='customerID' type='hidden' value='" + customerID
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

			//Update Customer Details

			public String updateCustomer(String ID, String name, String address, String email, String contact)
			{
			 String output = "";
			 try
			 {
		         con = DBConnect.connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE customer SET customerName=?,customerAddress=?,customerEmail=?,customerContact=? WHERE customerID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, name);
			 preparedStmt.setString(2, address);
			 preparedStmt.setString(3, email);
			 preparedStmt.setString(4, contact);
			 preparedStmt.setInt(5, Integer.parseInt(ID));

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
	
			// Delete Customer Details
	
			public String deleteCustomer(String customerID)
			 {
			 String output = "";
			 try
			 {
				 con = DBConnect.connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from customer where customerID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(customerID));
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

	
			// Search Customer Details

			public String viewProfile(String customerID) {

			String output = "";

			try {
				 con = DBConnect.connect();


			if (con == null) {
			return "Error while connecting to the database for reading.";
			}



			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th>" +
			"<th>Customer Address</th>" +
			"<th>Customer Email</th>" +

			"<th>Customer Contact</th>" +
			"<th>Update</th><th>Remove</th></tr>";



			String query = "select * from customer where customerID=' " + customerID + "'" ;

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);



			while (rs.next())
			 {
			 String customerID1 = Integer.toString(rs.getInt("customerID"));
			 String customerName = rs.getString("customerName");
			 String customerAddress = rs.getString("customerAddress");
			 String customerEmail = rs.getString("customerEmail");
			 String customerContact = rs.getString("customerContact");

			 // Add into the html table
			 output += "<tr><td>" + customerID1 + "</td>";
			 output += "<td>" + customerName + "</td>";
			 output += "<td>" + customerAddress + "</td>";
			 output += "<td>" + customerEmail + "</td>";
			 output += "<td>" + customerContact + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='customer.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='customerID' type='hidden' value='" + customerID
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
