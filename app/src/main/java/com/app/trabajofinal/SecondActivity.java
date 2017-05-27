package com.app.trabajofinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.app.trabajofinal.R;
import com.app.trabajofinal.object.CurrencyValue;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SecondActivity extends AppCompatActivity {
    private TextView Lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Lista=(TextView) findViewById(R.id.Lista);
        SeeList();
    }

    private void SeeList(){
        try {
            List<CurrencyValue> data = CurrencyValue.listAll(CurrencyValue.class);
            String[] Datos= new String[data.size()];
            for(int i=0; i<data.size();i++){
                Log.e("VALUE",data.get(i).getValue());
                Log.e("DATE",data.get(i).getDate());
                Datos[i]=(data.get(i).getValue()+" "+"DATE"+data.get(i).getDate()+",");
            }
                Lista.setText(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
