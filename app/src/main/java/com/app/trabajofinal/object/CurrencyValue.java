package com.app.trabajofinal.object;

import com.orm.SugarRecord;

/**
 * Created by verja on 26/05/2017.
 */

public class CurrencyValue extends SugarRecord<CurrencyValue> {

    private String date;
    private String value;

    public CurrencyValue(){
    }

    @Override
    public String toString() {
        return "Fecha:" + date + " " +
                "Valor:" + value + System.getProperty("line.separator");
    }

    public CurrencyValue(String date, String value){
        this.setValue(value);
        this.setDate(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
