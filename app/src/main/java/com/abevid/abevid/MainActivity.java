package com.abevid.abevid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanBtn, enterBtn;
    private TextView formatTxt, contentTxt, apiResult;
    protected static String scanContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //build scanner button
        scanBtn = (Button) findViewById(R.id.scan_button);
        enterBtn = (Button) findViewById(R.id.enter_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(this);
        enterBtn.setOnClickListener(this);


    }

    public void onClick(View v) {
        //respond to clicks
        if (v.getId() == R.id.scan_button) {
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        else {

                EditText scanText = (EditText) findViewById(R.id.upcNumber);
                scanContent = scanText.getText().toString();
                Log.d("MainActivity.onClick ", "Barcode:"+scanContent);
            if(scanContent!= null) {
                Intent intent = new Intent(this, ResultsActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "No value returned by scan", Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void onCheckboxClicked(View view){
        ArrayList al = new ArrayList();
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.calories:
                if (checked)
                    al.add("calories");
                else
                    break;
            case R.id.serving:
                if(checked)
                    al.add("serving_size");
                else
                    break;
        }
    }

}
