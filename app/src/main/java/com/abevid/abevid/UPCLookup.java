package com.abevid.abevid;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    // Default constructor
    public UPCLookup(){

    }


    /**
     * Constructor to call API method and pass in the required UPC barcode
     *
     * @param iUPC Barcode input from scanner
     * @param sKey Application Key for API
     * @param sID Application ID for API
     * @throws IOException IO Exception error
     * @throws ParseException Data read exception error
     */
    public UPCLookup(String iUPC, String sKey, String sID) throws IOException, ParseException {
        appID=sKey;
        appKey=sID;
        setMap(iUPC);

    }


    /**
     * Sets the Map of nutritional values received from the Nutritionix API
     *
     * @param input UPC barcode value received from a scanning application
     * @throws IOException Thrown exception from Inputstream
     * @throws ParseException Thrown exception from parsing the Inputstream
     */
    private void setMap(String input) throws IOException, ParseException {

        ObjectMapper map= new ObjectMapper();
        JSONParser jsonParser = new JSONParser();

        try {
            uNutritionix = new URL("https://api.nutritionix.com/v1_1/item?upc="+input+"&appId="+appID+"&appKey="+appKey).openConnection();

            // Parse input data stream
            InputStream	inputStream = uNutritionix.getInputStream();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));

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


}
