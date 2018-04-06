package com.abevid.abevid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }

    protected void displayResults(String upcN) throws IOException, ParseException {
        UPCLookup input = new UPCLookup(upcN);
        input.getMap();
    }
}
