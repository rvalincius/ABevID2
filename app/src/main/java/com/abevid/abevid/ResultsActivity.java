package com.abevid.abevid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Failing here **************************
        displayResults();
    }
    

    protected void displayResults(){
        String iUPC=MainActivity.scanContent;
        UPCLookup input = new UPCLookup(iUPC);
        //Map m = input.getMap();
        //TextView t=(TextView)findViewById(R.id.textView2);
        //String output=m.get("brand_name")+" "+m.get("item_name")+
        //        "\nCalories: "+m.get("nf_calories")+
        //        "\nServing Size: "+m.get("nf_serving_size_qty")+" " +m.get("nf_serving_size_unit");
        //t.setText(output);
        //Toast.makeText(this,output,Toast.LENGTH_LONG).show()




    }
}
