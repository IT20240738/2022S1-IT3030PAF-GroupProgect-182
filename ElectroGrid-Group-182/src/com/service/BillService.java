package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.model.Bill;

public class BillService {

Bill billObj = new Bill();
	
	//post API
	@POST
	@Path("/billAdd")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItemBill(@FormParam("billCode") String billCode,
			@FormParam("customerId") String customerId,
	@FormParam("month") String month,
	@FormParam("units") String units,
	@FormParam("KWHCharge") String KWHCharge,
	@FormParam("fixedCharge") String fixedCharge,
	@FormParam("rebate") String rebate,
	@FormParam("total") String total)
	{
		
	String output = billObj.insertItemBill(billCode,customerId, month, units, KWHCharge,fixedCharge,rebate,total);
	return output;
	}
	
	
	//get API
	@GET
	@Path("/bill")
	@Produces(MediaType.TEXT_HTML)
	public String readItemsBill()
	{
	return billObj.readItemsBill();
	
	}
	
}
