package com.example.emicalculator2bajud;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etLoanAmount, etInterestRate;
    private Spinner spLoanTerm;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLoanAmount = findViewById(R.id.editTextLoanAmount);
        etInterestRate = findViewById(R.id.editTextInterestRate);
        spLoanTerm = findViewById(R.id.spinnerLoanTerm);
        btnCalculate = findViewById(R.id.buttonCalculate);
        tvResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.loan_terms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoanTerm.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etLoanAmount.getText().toString().isEmpty() || etInterestRate.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter Loan Amount and Interest Rate", Toast.LENGTH_SHORT).show();
                    return;
                }

                double loanAmount = Double.parseDouble(etLoanAmount.getText().toString());
                double interestRate = Double.parseDouble(etInterestRate.getText().toString()) / 1200;
                int loanTerm = spLoanTerm.getSelectedItemPosition() + 1;

                double emi = (loanAmount * interestRate * Math.pow(1 + interestRate, loanTerm)) / (Math.pow(1 + interestRate, loanTerm) - 1);

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                tvResult.setText("EMI: Rs. " + decimalFormat.format(emi));
            }
        });
    }
}