package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DBConnect;

public class Bill {
	private static Connection con=null;

	//insert  bill
	public String insertItemBill(String billCode,String cusId, String month, String units,String KWHCharge,
			String fixedCharge,String rebate, String total)
	{
	String output = "";

	
	try
	{
	con = DBConnect.connect();
	if (con == null)
	{
		return "Error while connecting to the database for inserting.";
	}
	
	// create a prepared statement
	String query = " insert into bill (`billId`,`billCode`,`customerId`,`month`,`units`,`KWHCharge`,`fixedCharge`,`rebate`,`total`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	//allow to write parameterized queirs.PreparedStatement,
	//the Database uses an already compiled and defined access plan, this allows the prepared statement query to run 
	//faster than a normal query
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
	
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2,billCode);
	preparedStmt.setInt(3,Integer.parseInt(cusId));
	preparedStmt.setString(4, month);
	preparedStmt.setInt(5,Integer.parseInt(units));
	preparedStmt.setDouble(6,Double.parseDouble(KWHCharge));
	preparedStmt.setDouble(7,Double.parseDouble(fixedCharge));
	preparedStmt.setDouble(8,Double.parseDouble(rebate));
	preparedStmt.setDouble(9,Double.parseDouble(total));
	
	// execute the statement
	preparedStmt.execute();  
	
	con.close();
	
	output = "Bill Inserted successfully";
	}
	
	catch (Exception e)
	{
	output = "Error while inserting the bill.";
	System.err.println(e.getMessage());
	}
	
	return output;
	}
	
	
	//read items
	public String readItemsBill()
	{
		
	String output = "";
	
	try
	{
	con = DBConnect.connect();
	
	if (con == null)
	{
		return "Error while connecting to the database for reading.";
		
	}
	
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Bill Code</th>" +
			"<th>Customer Id</th>" +
			"<th>Month</th>" +
	"<th>Units</th>" +
	"<th>KWH Charge</th>" +
	"<th>Fixed Charge</th>" +
	"<th>Rebate</th>" +
	"<th>Total</th>" +
	"<th>Update</th><th>Remove</th></tr>";
	
	String query = "select * from bill";
	
	Statement stmt = con.createStatement();
	
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
	String billId = Integer.toString(rs.getInt("billId"));
	String billCode = rs.getString("billCode");
	String customerId = Integer.toString(rs.getInt("customerId"));
	String month = rs.getString("month");
	String units = Integer.toString(rs.getInt("units"));
	String KWHCharge=Double.toString(rs.getDouble("KWHCharge"));
	String fixedCharge=Double.toString(rs.getDouble("fixedCharge"));
	String rebate=Double.toString(rs.getDouble("rebate"));
	String total =Double.toString(rs.getDouble("total")); 
	
	// Add into the html table
	output += "<tr><td>" + billCode + "</td>";
	output += "<td>" + customerId + "</td>";
	output += "<td>" + month + "</td>";
	output += "<td>" + units + "</td>";
	output += "<td>" + KWHCharge + "</td>";
	output += "<td>" + fixedCharge + "</td>";
	output += "<td>" + rebate + "</td>";
	output += "<td>" + total + "</td>";
	
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	+ "<td><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	+ "</td></tr>";
	}
	
	con.close();
	
	// Complete the html table
	output += "</table>";
	}
	
	catch (Exception e)
	{
	output = "Error while reading the bill";
	
	System.err.println(e.getMessage());
	}
	
	return output;
	}
	

	
	
}
