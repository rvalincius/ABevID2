package com.abevid.abevid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.abevid.abevid.MainActivity.scanContent;

/**
 * Retrieves nutritional information from Nutritionix based on scanned UPC label
 * @author John Moser
 * @note CIS111B-ONLN Final project ABevID
 *
 */
public class UPCLookup {

	private URLConnection uNutritionix = null;
	private String appID,appKey;
	protected Map<String, Object> values = new HashMap<String,Object>();
	protected static JSONObject jsonFile;
	
	/**
	 * Constructor to call API method and pass in the required UPC barcode
	 * 
	 * @param iUPC Barcode input from scanner
	 * @throws IOException IO Exception error
	 * @throws ParseException Data read exception error
	 */
	public UPCLookup(String iUPC) {
		appID="247a5a79";
		appKey="89e7a52a56731cf95b5a0fab0d31b89a";

			AsyncTaskRunner apiCall = new AsyncTaskRunner();
			apiCall.execute();

	}
	
	
	/**
	 * Sets the Map of nutritional values received from the Nutritionix API
	 * 
	 * @param input UPC barcode value received from a scanning application
	 * @throws IOException Thrown exception from Inputstream
	 * @throws ParseException Thrown exception from parsing the Inputstream
	 */
	private void setMap(String input) throws ParseException {
		
		ObjectMapper map= new ObjectMapper();
		JSONParser jsonParser = new JSONParser();
		
		try {
		uNutritionix = new URL("https://api.nutritionix.com/v1_1/item?upc="+input+"&appId="+appID+"&appKey="+appKey).openConnection();

		// Parse input data stream
		InputStream	inputStream = uNutritionix.getInputStream();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));

		// create static JSON
			jsonFile=jsonObject;
			Log.d("*****JSONOBJECT*******",jsonFile.toJSONString());
		//convert JSON Object to JSON String
		String sJsonObject=jsonObject.toJSONString();

        //convert JSON string to Map
        values = map.readValue(sJsonObject, new TypeReference<HashMap<String, String>>() {});
		}

		// catch IOException errors
		catch (IOException	e)	{
			e.printStackTrace();
		}

	}

	/**
	 * Used to nutritional retrieve values stored within a Map
	 * @return Return Map of nutritional values
	 */
	public Map<String, Object> getMap() {
		
		return values;
	}
	
	
	/**
	 * Returns string value of data returned from the API
	 */
	public String toString() {
		return values.toString();
	
	}
	/*
	 * code below obtained from a combination of
	 * https://www.journaldev.com/9708/android-asynctask-example-tutorial
	 * and https://stackoverflow.com/questions/14250989/how-to-use-asynctask-correctly-in-android
	 */
	private class AsyncTaskRunner extends AsyncTask<Void, String, String> {

		private String resp;
		ProgressDialog progressDialog;

		@Override
		protected String doInBackground(Void... params) {

			try {
				setMap(scanContent);
			}
			catch(ParseException e){
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation
			//progressDialog.dismiss();
			//apiResult.setText(result);
		}


		@Override
		protected void onPreExecute() {
			//progressDialog = ProgressDialog.show(MainActivity.class,
			//		"ProgressDialog",
			//		"Retrieving data");
		}


		@Override
		protected void onProgressUpdate(String... text) {
			//apiResult.setText(text[0]);

		}
	}
}
