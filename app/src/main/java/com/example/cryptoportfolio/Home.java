package com.example.cryptoportfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class Home extends AppCompatActivity {
    private TextView qty;
    private Button btn;
    private RecyclerView recyclerView;
    private Spinner spin;
    private Button add;
    public JSONObject ja;
    private String[] token={"btcinr","ethinr","bnbinr","trxinr","eosinr"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MyListData[] myListData = new MyListData[]{
                new MyListData("btc", 60000),
                new MyListData("ETH", 1500),
        };
        AndroidNetworking.initialize(getApplicationContext());
        fetchdata();
        recyclerView = (RecyclerView) findViewById(R.id.RecycleView);
        spin=(Spinner)findViewById(R.id.spinner);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,token);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

         qty=(TextView)findViewById(R.id.editTextNumber);
         Log.d("Test",qty.getText().toString());
         add=(Button)findViewById(R.id.button2);
         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               //  if(qty.getText()!=null) myListData[myListData.length]=new MyListData(spin.getSelectedItem().toString(),qty.getText());
             }
         });
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public void fetchdata()
    {
        AndroidNetworking.get("https://api.wazirx.com/api/v2/tickers")
                .setPriority(Priority.LOW)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        // do anything with response
                        Log.d("Test","Response received");
                        try {
                            JSONObject jObject = new JSONObject(response);
                            ja=jObject;
                            /*btc.setText(jObject.getJSONObject("btcinr").getString("buy"));
                            eth.setText(jObject.getJSONObject("ethinr").getString("buy"));
                            ada.setText(jObject.getJSONObject("adainr").getString("buy"));
                            bnb.setText(jObject.getJSONObject("bnbinr").getString("buy"));
                            cake.setText("16419");*/
                            Log.d("Test", jObject.getJSONObject("btcinr").getString("buy"));
                        }
                        catch(Exception e) {}
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("Test","error"+error);
                    }
                });
    }


}
