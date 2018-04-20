package com.abevid.abevid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ResultsActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list=findViewById(R.id.listview);
        ArrayList<HashMap<String,String>> hmArray = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Execute API lookup
        try {
            Log.d("ResultsActivity: ", "Calling UPCLookup.getMap()");
            UPCLookup input = new UPCLookup();
            input.getMap();
        }
        catch(ExecutionException e){
            e.printStackTrace();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        //showData(UPCLookup.values);
    }

    public void showData(HashMap<String, String> input) {
        Adapter adapter = new Adapter(input);
        list.setAdapter(adapter);
    }

}
