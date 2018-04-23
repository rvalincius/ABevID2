package com.abevid.abevid;

import android.content.Intent;
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

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanBtn, enterBtn;
    public static CheckBox cServings,cVitA,cVitC,cFats,cCholest,cCalcium,cSodium,cIron,cCarbs,cFiber,cProtein,cGluten;
    private TextView formatTxt, contentTxt;
    protected static String scanContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //build buttons
        scanBtn = findViewById(R.id.scan_button);
        enterBtn = findViewById(R.id.enter_button);

        //build checkboxes
        formatTxt = findViewById(R.id.scan_format);
        contentTxt = findViewById(R.id.scan_content);
        cServings=findViewById(R.id.servings);
        cVitA=findViewById(R.id.vitA);
        cFats=findViewById(R.id.fats);
        cVitC=findViewById(R.id.vitC);
        cCholest=findViewById(R.id.cholest);
        cCalcium=findViewById(R.id.calcium);
        cSodium=findViewById(R.id.sodium);
        cIron=findViewById(R.id.iron);
        cCarbs=findViewById(R.id.carbs);
        cFiber=findViewById(R.id.fiber);
        cProtein=findViewById(R.id.protein);
        cGluten=findViewById(R.id.gluten);

        scanBtn.setOnClickListener(this);
        enterBtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        
        //respond to clicks
        //  Click for Scan Button
        if (v.getId() == R.id.scan_button) {
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        //  Click for Enter Button
        else {

                EditText scanText = findViewById(R.id.upcNumber);
                scanContent = scanText.getText().toString();
                Log.d("MainActivity.onClick ", "Barcode:"+scanContent);

            // New location to Execute API lookup to prevent null reporting
            try {
                Log.d("ResultsActivity: ", "Calling UPCLookup.getMap()");
                UPCLookup input = new UPCLookup();
                input.getMap();
            }

            // Error handling
            catch(ExecutionException e){
                e.printStackTrace();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

            if(scanContent!= null && UPCLookup.values.size() > 0) {

                Intent intent = new Intent(this, ResultsActivity.class);
                startActivity(intent);

            }else {
                if(scanContent!= null){
                    Toast.makeText(getApplicationContext(), "No value returned by scan", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"No data found for item",Toast.LENGTH_LONG).show();
                }

            }
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //Valid barcode scanned
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            EditText editText = findViewById(R.id.upcNumber);
            editText.setText(scanContent, TextView.BufferType.EDITABLE);
        }
        else{
            Toast.makeText(getApplicationContext(), "No value returned by scan", Toast.LENGTH_SHORT).show();
        }
    }
}
