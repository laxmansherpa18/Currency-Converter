package com.convertor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
public class MainActivity extends AppCompatActivity {

    private static final double EXCHANGE_RATE = 82.50; // Example conversion rate from USD to INR

    private EditText etAmount;
    private TextView tvConvertedAmount;
    private Button btnConvert;
    private Switch switchDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.et_amount);
        tvConvertedAmount = findViewById(R.id.tv_converted_amount);
        btnConvert = findViewById(R.id.btn_convert);
        switchDarkMode = findViewById(R.id.switch_dark_mode);

        // Set listener for dark mode switch
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Set listener for convert button
        btnConvert.setOnClickListener(v -> {
            String amountStr = etAmount.getText().toString();
            if (amountStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            double convertedAmount = convertCurrency(amount);
            tvConvertedAmount.setText(String.format("Converted Amount: %.2f INR", convertedAmount));
        });
    }

    // Currency conversion logic
    private double convertCurrency(double amount) {
        return amount * EXCHANGE_RATE;
    }
}
