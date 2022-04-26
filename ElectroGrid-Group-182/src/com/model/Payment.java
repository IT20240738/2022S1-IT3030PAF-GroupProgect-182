package com.model;

import com.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;

public class Payment {
	
	private static Connection con = null;
	
	
	//Insert payment detail
	public String insertPaymentDetails(String customerID, String customerName, String paymentType, String cardNo, String amount, String date, String billNo)
	{
		String output = "";
		
		try
		{
			con = DBConnect.connect();
   		   
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
			output = "Payment detail inserted successfully";
			
		}catch (Exception e)
		 {
			 output = "Error while inserting the payment detail.";
			 System.err.println(e.getMessage());
		 }
		    return output;
	}
	
	
	//Read payment list
		public String readPaymentDetails()
	    {
		   String output = "";
		   
		   try
		   {
			   con = DBConnect.connect();
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
			 output = "Error while reading the payment details.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		
		
		//Update payment details
		
		public String updatePaymentDetails(String paymentNo,String customerID, String customerName, String paymentType, String cardNo, String amount, String date, String billNo)
		{
			String output = "";
			
			try
			{
				con = DBConnect.connect();
				
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				
				// create a prepared statement
				String query = "UPDATE payment SET customerID=?,customerName=?,paymentType=?,cardNo=?, paymentAmount=?, paymentDate=?, billNo=? WHERE paymentNo=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				// binding values
		
				preparedStmt.setString(1, customerID);
				preparedStmt.setString(2, customerName);
				preparedStmt.setString(3, paymentType);
				preparedStmt.setString(4, cardNo);
				preparedStmt.setDouble(5, Double.parseDouble(amount));
				preparedStmt.setString(6, date);
				preparedStmt.setString(7,(billNo));
				preparedStmt.setInt(8, Integer.parseInt(paymentNo));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Payment detail updated successfully";
		   }
		   catch (Exception e)
		  {
				 output = "Error while updating the payment details.";
				 System.err.println(e.getMessage());
		  }
			
		   return output;
		   
		  }
		

		//Delete Payment Details
		public String deletePaymentDetails(String paymentNo)
		{
			String output = "";
			
			try
			{
				con = DBConnect.connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				 
				 // create a prepared statement
				 String query = "delete from payment where paymentNo=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(paymentNo));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Payment details deleted successfully";
			}
			
			catch (Exception e)
			{
				 output = "Error while deleting the payment details.";
				 System.err.println(e.getMessage());
		    }
			
			return output;
			
		 }
		
		
		//get payment detail using payment no
		
		public String PaymentDetails(String paymentNo1)
	    {
		   String output = "";
		   
		   try
		   {
			   con = DBConnect.connect();
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
		     

		     String query = "select *  from payment where paymentNo =' " + paymentNo1 + "'" ;
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
			 output = "Error while reading the payment details.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		

		//Get payment history
		public String PaymentHistory(String customerId)
	    {
		   String output = "";
		   
		   try
		   {
			   con = DBConnect.connect();
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
		     

		     String query = "select *  from payment where customerID =' " + customerId + "'" ;
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
			 output = "Error while reading the payment details.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		
		
		

	
}
