package com.service;

import java.sql.SQLException;
import java.util.ArrayList;

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
//import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Payment;


@Path("/Payment")
public class PaymentService {
	
	Payment paymentObj = new Payment();
	
	//Read Details
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML) 
		
		public String readPayment()
		{
			return paymentObj.readPaymentDetails();
		} 
	
	//Add Details
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertPayment(@FormParam("customerID") String customerID,
		 @FormParam("customerName") String customerName,
		 @FormParam("paymentType") String paymentType,
		 @FormParam("cardNo") String cardNo,
		 @FormParam("paymentAmount") String amount,
		 @FormParam("paymentDate") String date,
		 @FormParam("billNo") String billNo)
		
	
		{
		   String output = paymentObj.insertPaymentDetails(customerID,customerName,paymentType,cardNo, amount,date,billNo);
		 
		   return output;
		 
		}
		
		//Update Details
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
		//Convert the input string to a JSON object
		 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the values from the JSON object
		 String paymentNo = paymentObject.get("paymentNo").getAsString();
		 String customerID = paymentObject.get("customerID").getAsString();
		 String customerName = paymentObject.get("customerName").getAsString();
		 String paymentType = paymentObject.get("paymentType").getAsString();
		 String cardNo = paymentObject.get("cardNo").getAsString();
		 String paymentAmount = paymentObject.get("paymentAmount").getAsString();
		 String paymentDate = paymentObject.get("paymentDate").getAsString();
		 String billNo = paymentObject.get("billNo").getAsString();
		 
		 String output = paymentObj.updatePaymentDetails(paymentNo,customerID,customerName,paymentType,cardNo,paymentAmount,paymentDate,billNo);
		  
		 return output;
		 
		}
		
		
		//Delete Payment Details
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentDetails)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentDetails, "", Parser.xmlParser());

		//Read the value from the element <paymentID>
		 String paymentNo = doc.select("paymentNo").text();
		 String output = paymentObj.deletePaymentDetails(paymentNo);
		 
		 return output;
		 
		}
		
		
		// view payment details
		@GET
		@Path("/customer/{paymentNo}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		//@Produces(MediaType.TEXT_HTML)
		public String readprofile(@PathParam("paymentNo") String paymentNo) {

			return paymentObj.PaymentDetails(paymentNo);


		}

		// view payment history
		@GET
		@Path("/history/{customerId}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		//@Produces(MediaType.TEXT_HTML)
		public String paymentHistory(@PathParam("customerID") String customerID) {

			return paymentObj.PaymentHistory(customerID);


		}

		

		
		
	


}


