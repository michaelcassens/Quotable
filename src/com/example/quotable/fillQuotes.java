package com.example.quotable;

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

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class fillQuotes extends AsyncTask<String, String, ArrayList<MyObject>> {

	private TextView T = null;
	private String method;
	private String result;
	private String id;
	private String mainText;
	private Activity M;
	private ProgressBar B;
	private String ipAddress;
	private int finderID = 0;
	private String finderName = "";
	private int currentNumber = 0;
	
	public fillQuotes(Activity M, TextView T, String method, String id,
			String mainText, String finderName, int finderID, ProgressBar B,
			String ipAddress, int currentNumber) {
		this.T = T;
		this.M = M;
		this.method = method;
		this.id = id;
		this.mainText = mainText;
		this.finderName = finderName;
		this.finderID = finderID;
		this.B = B;
		this.ipAddress = ipAddress;
		this.currentNumber = currentNumber;
	}

	//@Override
	/*protected void onPostExecute(String result) {
		super.onPostExecute(result);

		this.result = result;
		JSONArray myArray = readJSON();

		ArrayList<MyObject> myList = new ArrayList<MyObject>();

		for (int i = 0; i < myArray.length(); i++) {
			try {

				JSONObject jo = myArray.getJSONObject(i);

				myList.add(new MyObject(jo.getString(id), jo
						.getString(mainText)));

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

			if(currentNumber >= myList.size()-1)
			{
				currentNumber = myList.size()-1; 
			}
			
			Log.d("currentNumber: ", currentNumber+"");
			T.setText(myList.get(currentNumber).getKEY_SETNAME().toString());
			
		

		if (B != null) {
			B.setVisibility(View.GONE);
		}

	}
	*/
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected ArrayList<MyObject> doInBackground(String... params) {
		//try {

			String test = "";
			test = invokeCategoryJSON(ipAddress, finderName,finderID);
			this.result = test;
			JSONArray myArray = readJSON();

			ArrayList<MyObject> myList = new ArrayList<MyObject>();

			for (int i = 0; i < myArray.length(); i++) {
				try {

					JSONObject jo = myArray.getJSONObject(i);

					myList.add(new MyObject(jo.getString(id), jo
							.getString(mainText), jo.getString("author")));

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			return myList;
//			if (test != null) {
	//		return test;
		//	} else {
		//		return "No string.";
			//}
	//	} catch (Exception e) {
			//return "Network problem";
		//}

	}


	// Method which invoke web methods
	public String invokeCategoryJSON(String IPAddress, String name, int value) {

		String responseJSON = "";

		try {

			// Create request
			SoapObject request = new SoapObject("http://tempuri.org/",
					method);
			// Property which holds input parameters
			PropertyInfo paramPI = new PropertyInfo();
			// Set Name
			paramPI.setName(name);
			// Set Value
			paramPI.setValue(Integer.valueOf(value));
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
			// make sure you have the ip in there so that it doesn't loop back
			// on
			// itself.
			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					"http://" + IPAddress + "/QuotableWeb/getInfo.asmx");

			// Invoke web service
			androidHttpTransport.call("http://tempuri.org/" + method, envelope);
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			// Assign it to static variable

			responseJSON = response.toString();

		} catch (Exception e) {
			// responseJSON += e.getMessage();
			// Log.setStackTraceString(e.printStackTrace());
		}

		return responseJSON;

	}

	public JSONArray readJSON() {
		JSONArray myArray = null;
		try {
			JSONObject jObject = new JSONObject(result);
			myArray = jObject.getJSONArray("cat");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// category += e.getMessage();
		}

		return myArray;

	}



}

