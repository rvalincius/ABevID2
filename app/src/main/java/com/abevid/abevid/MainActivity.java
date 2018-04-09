package com.abevid.abevid;

import android.content.DialogInterface;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button scanBtn, enterBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //build scanner button
        scanBtn = (Button)findViewById(R.id.scan_button);
        enterBtn = (Button)findViewById(R.id.enter_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(this);
        enterBtn.setOnClickListener(this);

    }

    public void onClick(View v){
        //respond to clicks
        if(v.getId()==R.id.scan_button){
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        else{

            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //Valid barcode scanned
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            EditText editText = (EditText)findViewById(R.id.upcNumber);
            editText.setText(scanContent,TextView.BufferType.EDITABLE);

        }

    }


    public void buttonOnClick (View v)
    {
        Button button = (Button) v;
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

}
