package com.app.trabajofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.trabajofinal.object.CurrencyValue;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String url="https://openexchangerates.org/api/latest.json?app_id=a3a79bb766be4a64a6647be3bb46e5a8";
    private TextView value, lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value=(TextView)findViewById(R.id.value);
        lastUpdate=(TextView)findViewById(R.id.date);
        getData();
    }

    private void getData(){
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(url, new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    // called before request is started
                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                    Log.e("SUCCESS","OK");
                    try {
                        String str = new String(responseBody, "UTF-8");
                        JSONObject json=new JSONObject(str);
                        JSONObject jsonCurrency=json.getJSONObject("rates");
                        String currency=String.valueOf(jsonCurrency.getDouble("COP"));
                        //Log.e("TOTAL",str);
                        //String cut=str.substring(125,129);
                        //Log.e("RESPONSE",cut);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String currentDateandTime = sdf.format(new Date());
                        value.setText(currency);
                        lastUpdate.setText(currentDateandTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e("ERROR","OK");
                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh(View view) {
        getData();
    }

    public void save(View view) {
        CurrencyValue currency = new CurrencyValue(lastUpdate.getText().toString(),value.getText().toString());
        currency.save();
    }

    public void history(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
    public void Delete(View view){
        List<CurrencyValue> books = CurrencyValue.listAll(CurrencyValue.class);

        CurrencyValue.deleteAll(CurrencyValue.class);
    }
}
