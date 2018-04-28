package com.abevid.abevid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
import android.util.Log;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.abevid.abevid.MainActivity.scanContent;

/**
 * The class performs an API call via a background process to the Nutritionix database.
 * A UPC label is passed to the API and a JSON Object is returned.
 *
 * <ul>** More information is available about the API at <a href=https://developer.nutritionix.com/docs/v1_1 target="_blank">Nutritionix.com</a></ul>
 * @author John Moser
 *
 */
public class UPCLookup {

    private String appID,appKey;
    protected static HashMap<String, String> values = new HashMap<>();

    /**
     * Constructor to call API method and pass in the required UPC barcode
     *
     */
    public UPCLookup() {
        // Throw line item to log for tracking
        Log.d("UPCLOOKUP","Main constructor");
    }


    /**
     * Sets the Map of nutritional values received from the Nutritionix API
     *
     * @param input UPC barcode value received from a scanning application
     * @throws ParseException Thrown exception from parsing the Inputstream
     */
    private void setMap(String input) throws ParseException {
        String sJsonObject;

        // Execute API
        Log.d("UPCLookup:","API Call starting");
        try {
            URLConnection uNutritionix = new URL("https://api.nutritionix.com/v1_1/item?upc="+input+"&appId="+appID+"&appKey="+appKey).openConnection();

            // Parse input data stream
            JSONParser jsonParser = new JSONParser();
            InputStream	inputStream = uNutritionix.getInputStream();

            // create static JSON
            JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));

            //convert JSON Object to JSON String
            sJsonObject=jsonObject.toJSONString();
            Log.d("JSON String:",sJsonObject);

            //convert JSON string to Map
            ObjectMapper map= new ObjectMapper();
            values = map.readValue(sJsonObject, new TypeReference<HashMap<String, String>>() {});

            Log.d("UPCLookup.setMap():",values.toString());

        }

        // catch IOException errors
        catch (IOException	e)	{
            Log.d("UPCLookup", "IOException Error");
            e.printStackTrace();
        }

    }


    /**
     * Instantiates the background task and calls the AsyncTask for executing the API for the application
     * @throws InterruptedException if connection is interrupted
     * @throws ExecutionException if the execution of the API returns an exception
    */
    public void getMap() throws InterruptedException, ExecutionException{
        appID="247a5a79";
        appKey="89e7a52a56731cf95b5a0fab0d31b89a";

        // Throw line item to log for tracking
        Log.d("UPCLookup.getMap():","getMap started");

        //Start API background processing
        AsyncTaskRunner apiCall = new AsyncTaskRunner();
        apiCall.execute().get();
    }


    /**
     * Returns string value of data returned from the API
     */
    public String toString() {
        return values.toString();
    }

    /**
     * Class used to initialize and execute a background process.  This background process is needed
     * in order to successfully execute the API call.
     */
    /*
     * code below obtained from a combination of
     * https://www.journaldev.com/9708/android-asynctask-example-tutorial
     * and https://stackoverflow.com/questions/14250989/how-to-use-asynctask-correctly-in-android
     */
    private class AsyncTaskRunner extends AsyncTask<Void, String, String> {

        //private String resp;
        //ProgressDialog progressDialog;

        /**
         * Used to execute processes in the background via a new thread
         * @param params Needed parameters for processing within the Background Task
         * @return a null string value.
         */
        @Override
        protected String doInBackground(Void... params) {
            Log.d("doInBackground","Starting AsyncTaskRunner");
            try {
                Log.d("doInBackground",scanContent);
                setMap(scanContent);
            }
            catch(ParseException e){
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Used to process needed transactions after TaskRunner processes complete
         * @param result String passed into method for Post TaskRunner execution
         */
        @Override
        protected void onPostExecute(String result) {
            /*
             *   Currently not used.  Possible enhancement
             */
            // execution of result of Long time consuming operation
            //progressDialog.dismiss();
            //apiResult.setText(result);
        }


        /**
        *   Used to establish any needed dialog information before running the
        *   TaskRunner process
         */
        @Override
        protected void onPreExecute() {
            /*
            *   Currently not used.  Possible enhancement
             */
            //progressDialog = ProgressDialog.show(ResultsActivity.class,
            //		"ProgressDialog",
            //		"Retrieving data");
        }


        /**
        * Used to provide a status update for the TaskRunner process
         */
        protected void onProgressUpdate(Integer... count) {
            /*
            *   Currently not used.  Possible enhancement
             */
            //apiResult.setText(count[0]);
        }
    }
}