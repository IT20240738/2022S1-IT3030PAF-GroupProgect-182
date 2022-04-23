package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
			return paymentObj.readPayment();
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

}


