package com.zeathon.istockinventory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DcStock extends Fragment {
    // TextView textView;

    EditText dt, opBal, rel1, rel2;
    TextView clBal;
    Spinner custName, locatAdr, prodtName;
    Button submitBn;
    DataBHelper dataBHelper;
    DatePickerDialog datePickerDialog;
    public DcStock(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_dc, container, false);
        // textView = view.findViewById(R.id.text);
        //textView.setText("first");

        dataBHelper = new DataBHelper(getActivity());

        dt = view.findViewById(R.id.dt);
        opBal = view.findViewById(R.id.opBal);
        clBal = view.findViewById(R.id.clBal);
        rel1 = view.findViewById(R.id.rel1);
        rel2 = view.findViewById(R.id.rel2);

        custName = view.findViewById(R.id.custName);
        locatAdr = view.findViewById(R.id.locatAdr);
        prodtName = view.findViewById(R.id.prodtName);
        submitBn = view.findViewById(R.id.submitBtn);


        dt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });



        TextWatcher autoAddTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double myNum1 = TextUtils.isEmpty(opBal.getText().toString()) ? 0 : Double.parseDouble(opBal.getText().toString());
                double myNum2 = TextUtils.isEmpty(rel1.getText().toString()) ? 0 : Double.parseDouble(rel1.getText().toString());
                double myNum3 = TextUtils.isEmpty(rel2.getText().toString()) ? 0 : Double.parseDouble(rel2.getText().toString());

                double sum = myNum2 + myNum3;
                double dif = myNum1 - sum;
                clBal.setText(Double.toString(dif));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        opBal.addTextChangedListener(autoAddTextWatcher);
        rel1.addTextChangedListener(autoAddTextWatcher);
        rel2.addTextChangedListener(autoAddTextWatcher);



        submitBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    final String custmName = custName.getSelectedItem().toString();
                    final String address = locatAdr.getSelectedItem().toString();
                    final String prodName = prodtName.getSelectedItem().toString();

                    final String dat = dt.getText().toString();
                    final String openBal = opBal.getText().toString();
                    final String relzo = rel1.getText().toString();
                    final String relzt = rel2.getText().toString();
                    final String closBal = clBal.getText().toString();

                    if (custmName.isEmpty()) {
                        Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (address.isEmpty()) {
                        Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                    }
                    if (prodName.isEmpty()) {
                        Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (dat.isEmpty()) {
                        dt.setError("some fields are empty");
                        dt.requestFocus();
                        return;
                    }
                    if (openBal.isEmpty()) {
                        opBal.setError("some fields are empty");
                        opBal.requestFocus();
                        return;
                    }
                    if (relzo.isEmpty()) {
                        rel1.setError("some fields are empty");
                        rel1.requestFocus();
                        return;
                    }
                    if (relzt.isEmpty()) {
                        rel2.setError("some fields are empty");
                        rel2.requestFocus();
                        return;
                    }
                    if (closBal.isEmpty()) {
                        clBal.setError("some fields are empty");
                        clBal.requestFocus();
                        return;
                    }

                    dataBHelper.addData(custmName, address, prodName, dat, openBal, relzo, relzt, closBal);
                    Toast.makeText(getActivity(), "Submitted...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), CargoActivity.class);
                    startActivity(intent);
                }



        });
        return view;
            }

    public void showDatePicker(){
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");



    }
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            dt.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(year));
        }
    };





    /*public void onClick(View v){
        switch (v.getId()){
            case R.id.submitBtn:
                dataSubmit();
            break;
        }*/
    }






