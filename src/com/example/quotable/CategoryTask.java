package com.example.quotable;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class CategoryTask extends AsyncTask<String, String, String> {

	private Spinner S;
	private String method;
	private String result;
	private String id;
	private String mainText;
	private MainActivity M;
	private ProgressBar B;
	
	public CategoryTask(MainActivity M, Spinner S,
			String method, String id, String mainText, ProgressBar B)
	{
		this.S = S;
		this.M = M;
		this.method = method;
		this.id = id;
		this.mainText = mainText;
		this.B = B;
	}
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		this.result = result;
		JSONArray myArray = readJSON();

        ArrayList<MyObject> myList = new ArrayList<MyObject>();
     
		for(int i = 0; i < myArray.length();i++)
		{
			try {

				JSONObject jo = myArray.getJSONObject(i);
				
				myList.add(new MyObject(jo.getString(id), 
						jo.getString(mainText)));
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		 ArrayAdapter<MyObject> dataAdapter = new ArrayAdapter<MyObject>(M,android.R.layout.simple_spinner_item, myList);
                 dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                 S.setAdapter(dataAdapter);
                 
                
          if(B != null)
          {
        	  B.setVisibility(View.GONE);
          }

	}

	
		@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			//String test = invokeCategoryJSON("192.168.1.9:8080");
			String test = invokeCategoryJSON("10.17.5.73:8080");
			
			if (test != null) {
				return test;
			} else {
				return "No string.";
			}
		} catch (Exception e) {
			return "Network problem";
		}

	}
	
	// Method which invoke web methods
	public String invokeCategoryJSON(String IPAddress) {
		// Create request
		SoapObject request = new SoapObject("http://tempuri.org/",
				"getCategories");
		// Property which holds input parameters
		// PropertyInfo paramPI = new PropertyInfo();
		// Set Name
		// paramPI.setName("country");
		// Set Value
		// paramPI.setValue(country);
		// Set dataType
		// paramPI.setType(String.class);
		// Add the property to request object
		// request.addProperty(paramPI);
		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		// make sure you have the ip in there so that it doesn't loop back on
		// itself.
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				"http://" + IPAddress + "/QuotableWeb/getInfo.asmx");
		String responseJSON = "";
		try {
			// Invole web service
			androidHttpTransport.call("http://tempuri.org/" + method,
					envelope);
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			// Assign it to static variable
			
			responseJSON = response.toString();

		} catch (Exception e) {
			responseJSON += e.getMessage();
			e.printStackTrace();
		}

		return responseJSON;
	}


	public JSONArray readJSON()
	{
		JSONArray myArray = null;
		try {
			JSONObject jObject = new JSONObject(result);
			myArray = jObject.getJSONArray("cat");
				
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//category += e.getMessage();
		}
		
		return myArray;
		
	}
}
