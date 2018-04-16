package com.abevid.abevid;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSONParser extends ListActivity {

    private ArrayAdapter<String> adapter;
    /**
     * Called when the activity is first created.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.populate());
        setListAdapter(adapter);

        populate();
    }

    private ArrayList<String> populate() {
        ArrayList<String> items = new ArrayList<String>();

        try {
            URL url = new URL("https://api.nutritionix.com/v1_1/item?upc=");
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // gets the server json data
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
            String next;
            while ((next = bufferedReader.readLine()) != null){
                JSONArray ja = new JSONArray(next);

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    items.add(jo.getString("text"));
                }
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return items;
    }
}
