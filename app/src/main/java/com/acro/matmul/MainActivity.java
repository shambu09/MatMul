package com.acro.matmul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ArrayList<Double>> X = new ArrayList<ArrayList<Double>>();
    List<ArrayList<Double>> Y = new ArrayList<ArrayList<Double>>();
    List<ArrayList<Double>> XY = new ArrayList<ArrayList<Double>>();

    List<EditText> eX = new ArrayList<EditText>();
    List<EditText> eY = new ArrayList<EditText>();
    List<TextView> eXY = new ArrayList<TextView>();

    DecimalFormat formatHandler = new DecimalFormat("0.####");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        int temp;
        int temp1;
        int temp2;

        EditText x;
        EditText y;
        TextView xy;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = i * 3 + j;
                x = (EditText) findViewById(getResources().getIdentifier("editText" + Integer.toString(temp), "id", getPackageName()));
                temp1 = temp + 9;
                y = (EditText) findViewById(getResources().getIdentifier("editText" + Integer.toString(temp1), "id", getPackageName()));
                temp2 = temp + 3;
                xy = (TextView) findViewById(getResources().getIdentifier("textView" + Integer.toString(temp2), "id", getPackageName()));
                eX.add(x);
                eY.add(y);
                eXY.add(xy);
            }
        }

        clearRef();
    }

    public void build(){
        int temp;
        double value;
        String s;

        EditText x;
        EditText y;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = i * 3 + j;
                x = eX.get(temp);
                y = eY.get(temp);

                s = x.getText().toString();
                X.get(i).set(j, parse(s));

                s = y.getText().toString();
                Y.get(i).set(j, parse(s));
            }
        }
    }

    public double parse(String s){
        if (s.equals(""))
            return 0.0;
        else
            return Double.parseDouble(s);
    }

    public void set() {
        int temp;
        String v;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = i * 3 + j;
                v = formatHandler.format(XY.get(i).get(j));
                eXY.get(temp).setText(v);
            }
        }
    }

    public void compute(View view) {
        build();
        fill();
        double a;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a = 0;
                for (int k = 0; k < 3; k++) {
                    a += X.get(i).get(k) * Y.get(k).get(j);
                }
                XY.get(i).set(j, a);
            }
        }
        set();
    }

    public void fill_zeros(View view){
        build();
        fill();
    }

    public void fill(){
        int temp;
        String v;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = i * 3 + j;

                v = formatHandler.format(X.get(i).get(j));
                eX.get(temp).setText(v);
                v = formatHandler.format(Y.get(i).get(j));
                eY.get(temp).setText(v);
            }
        }
    }

    public void clearRef(){
        X.clear();
        Y.clear();
        XY.clear();

        for (int j=0; j< 3; j++){
            X.add(new ArrayList<Double>(Collections.nCopies(3, 0.0)));
            Y.add(new ArrayList<Double>(Collections.nCopies(3, 0.0)));
            XY.add(new ArrayList<Double>(Collections.nCopies(3, 0.0)));
        }
    }

    public void clear(View view) {
        clearRef();
        fill();
        set();
    }

}
