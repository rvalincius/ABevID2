package com.abevid.abevid;

import com.abevid.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    private String upc;
    private EditText editText = (EditText)findViewById(R.id.upcNumber);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //build scanner button
        scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        //method to initiate click action
        switch(v.getId()) {
            case R.id.scan_button:
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();
                break;
            case R.id.enterbutton:

                Intent intent = new Intent(this, ResultsActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve the scanner results
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //execute if a barcode was successfully scanned
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //grab the result of the scanner and insert it into the upcNumber field
            editText.setText(scanContent,TextView.BufferType.EDITABLE);
            upc=scanContent;

        }
        //Inform user that no data was received from scanner
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
