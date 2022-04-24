package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Bill;

@Path("/bill")
public class BillService {

Bill billObj = new Bill();
	
	//post API
	@POST
	@Path("/billAdd")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItemBill(@FormParam("billCode") String billCode,
			@FormParam("customerID") String customerId,
	@FormParam("month") String month,
	@FormParam("units") String units,
	@FormParam("KWHCharge") String KWHCharge,
	@FormParam("fixedCharge") String fixedCharge,
	@FormParam("rebate") String rebate,
	@FormParam("total") String total)
	{
		
	String output = billObj.insertBill(billCode,customerId, month, units, KWHCharge,fixedCharge,rebate,total);
	return output;
	}
	
	
	//get API
	@GET
	@Path("/billAll")
	@Produces(MediaType.TEXT_HTML)
	public String readItemsBill()
	{
	return billObj.readBill();
	
	}
	
	
	//put API
		@PUT
		@Path("/billUpdate")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItemBill(String itemBillData)
		{
			
		//Convert the input string to a JSON object
		JsonObject itemBillObject = new JsonParser().parse(itemBillData).getAsJsonObject();
		
		//Read the values from the JSON object
		String billID = itemBillObject.get("billId").getAsString();
		String billCode = itemBillObject.get("billCode").getAsString();
		String cusId = itemBillObject.get("customerID").getAsString();
		String month = itemBillObject.get("month").getAsString();
		String units = itemBillObject.get("units").getAsString();
		String KWHCharge = itemBillObject.get("KWHCharge").getAsString();
		String fixedCharge = itemBillObject.get("fixedCharge").getAsString();
		String rebate = itemBillObject.get("rebate").getAsString();
		String total = itemBillObject.get("total").getAsString();
		
		String output = billObj.updateBill(billID,billCode, cusId, month, units,KWHCharge,fixedCharge,rebate, total);
		return output;
		}
		
		
		//delete API
		@DELETE
		@Path("/billDelete")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItemBill(String billData)
		{
			
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String billID = doc.select("billId").text();
		
		String output = billObj.deleteBill(billID);
		return output;
		}
		
		
		@GET
		@Path("/billView")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String getItem(String billItemData)
		{
			
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(billItemData, "", Parser.xmlParser());
		
		//Read the value from the element <billID>
		String billCode = doc.select("billCode").text();
		
		String output = billObj.getBill(billCode);
		return output;
		}
		

	
}
