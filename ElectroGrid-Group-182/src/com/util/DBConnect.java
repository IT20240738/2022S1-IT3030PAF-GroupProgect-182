package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	//A common method to connect to the DB
	
		private Connection connect()
		{
			 Connection con = null;
			 
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");

				 //Provide the correct details: DBServer/DBName, username, password
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electroGrid", "root", "hotel*123");
			 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 
			 return con;
		}

}
