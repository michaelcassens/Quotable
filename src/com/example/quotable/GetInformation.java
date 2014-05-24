package com.example.quotable;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

public class GetInformation {

	private String method;
	private String result;
	private String id;
	private String mainText;
	private String ipAddress;
	private int finderID = 0;
	private String finderName = "";

	public GetInformation(String method, String id, String mainText,
			String finderName, int finderID, String ipAddress) {
		this.method = method;
		this.id = id;
		this.mainText = mainText;
		this.finderName = finderName;
		this.finderID = finderID;
		this.ipAddress = ipAddress;

	}

	public ArrayList<MyObject> getInformation() {

		ArrayList<MyObject> myList = new ArrayList<MyObject>();

		String test = "";
		test = invokeCategoryJSON(ipAddress, finderName, finderID);

		result = test;
		JSONArray myArray = readJSON();

		
		for (int i = 0; i < myArray.length(); i++) {
			try {

				JSONObject jo = myArray.getJSONObject(i);

				myList.add(new MyObject(jo.getString(id), jo
						.getString(mainText)));

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		return myList;
	}

	public String invokeCategoryJSON(String IPAddress, String name, int value) {

		// Create request
		SoapObject request = new SoapObject("http://tempuri.org/",
				"getQuotesByCategory");
		// Property which holds input parameters
		PropertyInfo paramPI = new PropertyInfo();
		// Set Name
		paramPI.setName(name);
		// Set Value
		paramPI.setValue(value);
		// Set dataType
		paramPI.setType(Integer.class);
		// Add the property to request object
		request.addProperty(paramPI);
		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		// make sure you have the ip in there so that it doesn't loop back on
		// itself.
		//Log.d("transport instance:", "before");

		HttpTransportSE androidHttpTransport = new HttpTransportSE("http://"
				+ IPAddress + "/QuotableWeb/getInfo.asmx");
		
		String responseJSON = "";
		// try {
		// Invoke web service
		try {
			
			Log.d("call: ", "before");
			androidHttpTransport.call("http://tempuri.org/" + method, envelope);
			Log.d("call: ", "after");
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

			responseJSON = response.toString();

		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			//Log.d("IO: ", e.getMessage());
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
		//	Log.d("xml: ", e.getMessage());

			//e.printStackTrace();
		}
		//Log.d("transport call:", "after");
		// Get the response
		// Assign it to static variable

		// } catch (Exception e) {
		// Log.d("error: ", e.getMessage());
		// }

		return responseJSON;
	}

	public JSONArray readJSON() {
		JSONArray myArray = null;
		try {
			JSONObject jObject = new JSONObject(result);
			myArray = jObject.getJSONArray("cat");

		} catch (JSONException e) {

			//Log.d("read JSON: ", e.getMessage());
		}

		return myArray;

	}

}
