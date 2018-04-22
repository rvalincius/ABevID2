package com.abevid.abevid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
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

        showData();
    }

    public void showData() {
        // Construct the data source

        ArrayList<Items> arrayOfItems = new ArrayList<Items>();
        Items iBName= new Items("Brand Name",UPCLookup.values.get("brand_name"));
        Items iName= new Items("Item Name",UPCLookup.values.get("item_name"));
        Items iCalories= new Items("Calories",UPCLookup.values.get("nf_calories"));
        Items iServSize= new Items("Serving Size",UPCLookup.values.get("nf_serving_size_qty")+" "+UPCLookup.values.get("nf_serving_size_unit")+" ("+UPCLookup.values.get("nf_serving_weight_grams")+"grams)");
        Items iFatCal= new Items("Calories from fat",UPCLookup.values.get("nf_calories_from_fat"));
        Items iTotalFat= new Items("Total fat",UPCLookup.values.get("nf_total_fat"));
        Items iSatFat= new Items("Saturated fat",UPCLookup.values.get("nf_saturated_fat"));
        Items iTransFat= new Items("Trans fat",UPCLookup.values.get("nf_trans_fatty_acid"));
        Items iPolyFat= new Items("Polyunsaturated fat",UPCLookup.values.get("nf_polyunsaturated_fat"));
        Items iCholest= new Items("Monounsaturated fat",UPCLookup.values.get("nf_monounsaturated_fat"));
        Items iSodium= new Items("Sodium",UPCLookup.values.get("nf_sodium"));
        Items iTotCarb= new Items("Total Carbs",UPCLookup.values.get("nf_total_carbohydrate"));
        Items iFiber= new Items("Dietary Fiber",UPCLookup.values.get("nf_dietary_fiber"));
        Items iSugar= new Items("Total sugars",UPCLookup.values.get("nf_sugars"));
        Items iProtein= new Items("Total protein",UPCLookup.values.get("nf_protein"));
        Items iVitA= new Items("DV% vitamin A",UPCLookup.values.get("nf_vitamin_a_dv"));
        Items iVitC= new Items("DV% vitamin C",UPCLookup.values.get("nf_vitamin_c_dv"));
        Items iCalcium= new Items("DV% calcium",UPCLookup.values.get("nf_calcium_dv"));
        Items iIron= new Items("DV% iron",UPCLookup.values.get("nf_iron_dv"));

        arrayOfItems.add(iName);
        arrayOfItems.add(iBName);
        arrayOfItems.add(iCalories);
        arrayOfItems.add(iServSize);
        arrayOfItems.add(iFatCal);
        arrayOfItems.add(iTotalFat);
        arrayOfItems.add(iSatFat);
        arrayOfItems.add(iTransFat);
        arrayOfItems.add(iPolyFat);
        arrayOfItems.add(iCholest);
        arrayOfItems.add(iSodium);
        arrayOfItems.add(iTotCarb);
        arrayOfItems.add(iFiber);
        arrayOfItems.add(iSugar);
        arrayOfItems.add(iProtein);
        arrayOfItems.add(iVitA);
        arrayOfItems.add(iVitC);
        arrayOfItems.add(iCalcium);
        arrayOfItems.add(iIron);
        arrayOfItems.add(iProtein);

        // Create the itemAdapter to convert the array to views
        ItemAdapter itemAdapter = new ItemAdapter(this, arrayOfItems);
        // Attach the itemAdapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
    }

}
