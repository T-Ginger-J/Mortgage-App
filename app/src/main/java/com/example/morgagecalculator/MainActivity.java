package com.example.morgagecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView target;
    EditText interest;
    EditText period;
    EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        target = (TextView) findViewById(R.id.target);
        interest = (EditText) findViewById(R.id.Interest);
        period = (EditText) findViewById(R.id.Period);
        amount = (EditText) findViewById(R.id.Amount);

    }

    public void Calc (View view) {
        String iS = interest.getText().toString(); //get values from inputs
        String pS = period.getText().toString();
        String aS = amount.getText().toString();
        
        if (!iS.isEmpty() && !pS.isEmpty() && !aS.isEmpty()) { //check all values entered
            float i = Float.parseFloat(iS)/100/12; //convert from yearly % to monthly decimal
            int p = Integer.parseInt(pS)*12; //convert from months
            float a = Float.parseFloat(aS);

            double m = calculateMonthly(i, p, a); // run function

            NumberFormat currency = NumberFormat.getCurrencyInstance(); //convert to $1.11

            target.setText(currency.format(m)); //set monthly payment
        } else {
            target.setText("Blank Fields"); // error handling
        }
    }

    private double calculateMonthly (float i, int p, float a) {
        if (i == 0){
            return a / p;
        }
        return i * a * Math.pow(1+i, p) / (Math.pow(1+i, p) -1);

    }
}