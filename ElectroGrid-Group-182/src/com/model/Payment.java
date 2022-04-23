package com.model;

import com.util.DBConnect;

import java.sql.*;

public class Payment {
	
	private Connection connect()
	{
		 Connection con = null;
		 
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "hotel*123");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	}
	
	
	//Insert Item
	public String insertPaymentDetails(String customerID, String customerName, String paymentType, String cardNo, String amount, String date, String billNo)
	{
		String output = "";
		
		try
		{
   		   Connection con = connect();
   		   
		   if (con == null)
           {return "Error while connecting to the database for inserting."; }
		   
			 // create a prepared statement
			 String query = " insert into payment (`paymentNo`,`customerID`,`customerName`,`paymentType`,`cardNo`,`paymentAmount`,`paymentDate`,`billNo`)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerID);
			preparedStmt.setString(3, customerName);
			preparedStmt.setString(4, paymentType);
			preparedStmt.setString(5, cardNo);
			preparedStmt.setDouble(6, Double.parseDouble(amount));
			preparedStmt.setString(7, date);
			preparedStmt.setInt(8, Integer.parseInt(billNo));
			
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
		public String readPayment()
	    {
		   String output = "";
		   try
		   {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
		     // Prepare the html table to be displayed
		     output = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th>" +
					   "<th>Payment Type</th>" +
					   "<th>Card No</th>" +
					   "<th>Payment Amount</th>" +
					   "<th>Payment Date</th>" +
					   "<th>Bill No</th>" +
					   "<th>Update</th><th>Remove</th></tr>";
		     

			 String query = "select * from payment";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String paymentNo = Integer.toString(rs.getInt("paymentNo"));
				 String customerID = rs.getString("customerID");
				 String customerName = rs.getString("customerName");
				 String paymentType = rs.getString("paymentType");
				 String cardNo = rs.getString("cardNo");
				 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
				 String paymentDate = rs.getString("paymentDate");
				 String billNo = rs.getString("billNo");
				 
				 // Add into the html table
				 output += "<tr><td>" + customerID + "</td>";
				 output += "<td>" + customerName + "</td>";
				 output += "<td>" + paymentType + "</td>";
				 output += "<td>" + cardNo + "</td>";
				 output += "<td>" + paymentAmount + "</td>";
				 output += "<td>" + paymentDate + "</td>";
				 output += "<td>" + billNo + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='payment.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='itemID' type='hidden' value='" + paymentNo
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
		



	
}
