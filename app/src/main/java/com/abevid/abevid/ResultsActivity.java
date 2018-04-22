package com.abevid.abevid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Execute API lookup
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

        // Build the Array for the ListView and display it
        showData();
    }

    private void showData() {

        /*
         * Construct the ArrayList data source
         * This section needs to be adjusted and streamlined
         */
        ArrayList<Items> arrayOfItems = new ArrayList<>();

        // Build default items for list
        Items iBName= new Items("Brand Name",UPCLookup.values.get("brand_name"));
        arrayOfItems.add(iBName);
        Items iName= new Items("Item Name",UPCLookup.values.get("item_name"));
        arrayOfItems.add(iName);
        Items iCalories= new Items("Calories",UPCLookup.values.get("nf_calories"));
        arrayOfItems.add(iCalories);


        /*
        *   Building items from checkboxes
        */

        //  Build ListView items for user selected values
        if(MainActivity.cServings.isChecked()){
            Items iServSize= new Items("Serving Size",UPCLookup.values.get("nf_serving_size_qty")+" "+UPCLookup.values.get("nf_serving_size_unit")+" ("+UPCLookup.values.get("nf_serving_weight_grams")+"grams)");
            arrayOfItems.add(iServSize);
        }
        if(MainActivity.cFats.isChecked()) {
            Items iFatCal = new Items("Calories from fat", UPCLookup.values.get("nf_calories_from_fat"));
            Items iTotalFat = new Items("Total fat", UPCLookup.values.get("nf_total_fat"));
            Items iSatFat = new Items("Saturated fat", UPCLookup.values.get("nf_saturated_fat"));
            Items iTransFat = new Items("Trans fat", UPCLookup.values.get("nf_trans_fatty_acid"));
            Items iPolyFat = new Items("Polyunsaturated fat", UPCLookup.values.get("nf_polyunsaturated_fat"));
            Items iMonoFat = new Items("Monounsaturated fat", UPCLookup.values.get("nf_monounsaturated_fat"));
            arrayOfItems.add(iFatCal);
            arrayOfItems.add(iTotalFat);
            arrayOfItems.add(iSatFat);
            arrayOfItems.add(iTransFat);
            arrayOfItems.add(iPolyFat);
            arrayOfItems.add(iMonoFat);
        }
        if(MainActivity.cCholest.isChecked()) {
            Items iCholest = new Items("Cholesterol", UPCLookup.values.get("nf_cholesterol"));
            arrayOfItems.add(iCholest);
        }
        if(MainActivity.cSodium.isChecked()) {
            Items iSodium = new Items("Sodium", UPCLookup.values.get("nf_sodium"));
            arrayOfItems.add(iSodium);
        }
        if(MainActivity.cCarbs.isChecked()) {
            Items iTotCarb = new Items("Total Carbs", UPCLookup.values.get("nf_total_carbohydrate"));
            Items iSugar = new Items("Total sugars", UPCLookup.values.get("nf_sugars"));
            arrayOfItems.add(iTotCarb);
            arrayOfItems.add(iSugar);
        }
        if(MainActivity.cFiber.isChecked()) {
            Items iFiber = new Items("Dietary Fiber", UPCLookup.values.get("nf_dietary_fiber"));
            arrayOfItems.add(iFiber);
        }
        if(MainActivity.cProtein.isChecked()) {
            Items iProtein= new Items("Total protein",UPCLookup.values.get("nf_protein"));
            arrayOfItems.add(iProtein);
        }
        if(MainActivity.cVitA.isChecked()) {
            Items iVitA= new Items("DV% vitamin A",UPCLookup.values.get("nf_vitamin_a_dv"));
            arrayOfItems.add(iVitA);
        }
        if(MainActivity.cVitC.isChecked()) {
            Items iVitC= new Items("DV% vitamin C",UPCLookup.values.get("nf_vitamin_c_dv"));
            arrayOfItems.add(iVitC);
        }
        if(MainActivity.cCalcium.isChecked()) {
            Items iCalcium = new Items("DV% calcium", UPCLookup.values.get("nf_calcium_dv"));
            arrayOfItems.add(iCalcium);
        }
        if(MainActivity.cIron.isChecked()) {
            Items iIron= new Items("DV% iron",UPCLookup.values.get("nf_iron_dv"));
            arrayOfItems.add(iIron);
        }


         // Create the itemAdapter to convert the array to views
         ItemAdapter itemAdapter = new ItemAdapter(this, arrayOfItems);

         // Attach the itemAdapter to a ListView
         ListView listView = findViewById(R.id.listview);
         listView.setAdapter(itemAdapter);
    }

}
