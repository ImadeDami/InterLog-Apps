package com.zeathon.istockinventory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FmcgStock extends AppCompatActivity {
    EditText meas, dt, firstNum, secondNum, fstNum, secNum, extraPcs;
    TextView totalCP, totalRP, answer;
    Spinner custName, locatAdr, mktName, prodtName;
    DatePickerDialog datePickerDialog;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_fmcg);


        dataBaseHelper = new DataBaseHelper(this);

        meas = findViewById(R.id.meas);
        dt = findViewById(R.id.dt);
        firstNum = findViewById(R.id.firstNum);
        secondNum = findViewById(R.id.secondNum);
        fstNum = findViewById(R.id.fstNum);
        secNum = findViewById(R.id.secNum);
        totalCP = findViewById(R.id.totalCP);
        totalRP = findViewById(R.id.totalRP);
        extraPcs = findViewById(R.id.extraPcs);
        answer = findViewById(R.id.answer);

        custName = findViewById(R.id.custName);
        locatAdr = findViewById(R.id.locatAdr);
        mktName = findViewById(R.id.mktName);
        prodtName = findViewById(R.id.prodtName);

        dataSubmit();

        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                datePickerDialog = new DatePickerDialog(FmcgStock.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        dt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        TextWatcher autoAddTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double myNum1 = TextUtils.isEmpty(firstNum.getText().toString()) ? 0 :Double.parseDouble(firstNum.getText().toString());
                double myNum2 = TextUtils.isEmpty(secondNum.getText().toString()) ? 0 :Double.parseDouble(secondNum.getText().toString());

                double sum = myNum1*myNum2;
                totalCP.setText(Double.toString(sum));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        firstNum.addTextChangedListener(autoAddTextWatcher);
        secondNum.addTextChangedListener(autoAddTextWatcher);

        TextWatcher autoAddTextWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double myNub1 = TextUtils.isEmpty(fstNum.getText().toString()) ? 0 :Double.parseDouble(fstNum.getText().toString());
                double myNub2 = TextUtils.isEmpty(secNum.getText().toString()) ? 0 :Double.parseDouble(secNum.getText().toString());

                double summ = myNub1*myNub2;
                totalRP.setText(Double.toString(summ));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        fstNum.addTextChangedListener(autoAddTextWatcher2);
        secNum.addTextChangedListener(autoAddTextWatcher2);

        TextWatcher autoAddTextWatcher3 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double myTot1 = TextUtils.isEmpty(totalCP.getText().toString()) ? 0 :Double.parseDouble(totalCP.getText().toString());
                double myTot2 = TextUtils.isEmpty(totalRP.getText().toString()) ? 0 :Double.parseDouble(totalRP.getText().toString());
                double exPcs = TextUtils.isEmpty(extraPcs.getText().toString()) ? 0 :Double.parseDouble(extraPcs.getText().toString());

                double summm = myTot1+myTot2+exPcs;
                answer.setText(Double.toString(summm));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        totalCP.addTextChangedListener(autoAddTextWatcher3);
        totalRP.addTextChangedListener(autoAddTextWatcher3);
        extraPcs.addTextChangedListener(autoAddTextWatcher3);
    }

    private void dataSubmit() {
        final String custmName = custName.getSelectedItem().toString();
        final String address = locatAdr.getSelectedItem().toString();
        final String mrktName = mktName.getSelectedItem().toString();
        final String prodName = prodtName.getSelectedItem().toString();

        final String dat = dt.getText().toString();
        final String measureT = meas.getText().toString();
        final String numbCat = firstNum.getText().toString();
        final String qttyCat = secondNum.getText().toString();
        final String totCat = totalCP.getText().toString();

        final String numbRol = fstNum.getText().toString();
        final String qttyRol = secNum.getText().toString();
        final String totlRol = totalRP.getText().toString();
        final String extrPcs = extraPcs.getText().toString();

        final String totlPcs = answer.getText().toString();

        if (custmName.isEmpty()) {
            Toast.makeText(FmcgStock.this, "some fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (address.isEmpty()) {
            Toast.makeText(FmcgStock.this, "some fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mrktName.isEmpty()) {
            Toast.makeText(FmcgStock.this, "some fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (prodName.isEmpty()) {
            Toast.makeText(FmcgStock.this, "some fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dat.isEmpty()) {
            dt.setError("some fields are empty");
            dt.requestFocus();
            return;
        }
        if (measureT.isEmpty()) {
            meas.setError("some fields are empty");
            meas.requestFocus();
            return;
        }
        if (numbCat.isEmpty()) {
            firstNum.setError("some fields are empty");
            firstNum.requestFocus();
            return;
        }
        if (qttyCat.isEmpty()) {
            secondNum.setError("some fields are empty");
            secondNum.requestFocus();
            return;
        }
        if (totCat.isEmpty()) {
            totalCP.setError("some fields are empty");
            totalCP.requestFocus();
            return;
        }
        if (numbRol.isEmpty()) {
            fstNum.setError("some fields are empty");
            fstNum.requestFocus();
            return;
        }
        if (qttyRol.isEmpty()) {
            secNum.setError("some fields are empty");
            secNum.requestFocus();
            return;
        }
        if (totlRol.isEmpty()) {
            totalRP.setError("some fields are empty");
            totalRP.requestFocus();
            return;
        }
        if (extrPcs.isEmpty()) {
            extraPcs.setError("some fields are empty");
            extraPcs.requestFocus();
            return;
        }
        if (totlPcs.isEmpty()) {
            answer.setError("some fields are empty");
            answer.requestFocus();
            return;
        }

        dataBaseHelper.addData(custmName, address, dat, mrktName, prodName, measureT, numbCat, qttyCat, totCat, numbRol, qttyRol, totlRol, extrPcs, totlPcs);
        Toast.makeText(FmcgStock.this, "Submitted...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FmcgStock.this, FmcgStock.class);
        startActivity(intent);


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.submitBtn:
                dataSubmit();
            break;
        }
    }
}

