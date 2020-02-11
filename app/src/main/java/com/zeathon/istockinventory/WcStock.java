package com.zeathon.istockinventory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class WcStock extends Fragment {
    // TextView textView;

    EditText cdt, rdt, tkOn, dpp, trkOut, meas;
    Spinner custName, locatAdr, prodtName;
    AutoCompleteTextView vesNam;
    Button submitBn;
    DBHelper dBHelper;
    DatePickerDialog datePickerDialog;
    public WcStock(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_wc, container, false);
        // textView = view.findViewById(R.id.text);
        //textView.setText("first");

        dBHelper = new DBHelper(getActivity());

        cdt = view.findViewById(R.id.cdt);
        rdt = view.findViewById(R.id.rdt);
        tkOn = view.findViewById(R.id.tkOn);
        dpp = view.findViewById(R.id.dpp);
        trkOut = view.findViewById(R.id.trkOut);
        meas = view.findViewById(R.id.meas);
        vesNam = view.findViewById(R.id.vesNam);

        custName = view.findViewById(R.id.custName);
        locatAdr = view.findViewById(R.id.locatAdr);
        prodtName = view.findViewById(R.id.prodtName);

        submitBn = view.findViewById(R.id.submitBtn);

        cdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        rdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker2();
            }
        });

        submitBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String custmName = custName.getSelectedItem().toString();
                final String address = locatAdr.getSelectedItem().toString();
                final String prodName = prodtName.getSelectedItem().toString();

                final String cdat = cdt.getText().toString();
                final String rdat = rdt.getText().toString();
                final String vessNam = vesNam.getText().toString();
                final String takOn = tkOn.getText().toString();
                final String dip = dpp.getText().toString();
                final String trckOut = trkOut.getText().toString();

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

                if (cdat.isEmpty()) {
                    cdt.setError("some fields are empty");
                    cdt.requestFocus();
                    return;
                }
                if (rdat.isEmpty()) {
                    rdt.setError("some fields are empty");
                    rdt.requestFocus();
                    return;
                }
                if (vessNam.isEmpty()) {
                    vesNam.setError("some fields are empty");
                    vesNam.requestFocus();
                    return;
                }
                if (takOn.isEmpty()) {
                    tkOn.setError("some fields are empty");
                    tkOn.requestFocus();
                    return;
                }
                if (dip.isEmpty()) {
                    dpp.setError("some fields are empty");
                    dpp.requestFocus();
                    return;
                }
                if (trckOut.isEmpty()) {
                    trkOut.setError("some fields are empty");
                    trkOut.requestFocus();
                    return;
                }

                dBHelper.addData(custmName, address, prodName, cdat, rdat, vessNam, takOn, dip, trckOut);
                Toast.makeText(getActivity(), "Submitted...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), CargoActivity.class);
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

            cdt.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(year));
        }
    };

    public void showDatePicker2(){
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
        date.setCallBack(ondate2);
        date.show(getFragmentManager(), "Date Picker");

    }
    DatePickerDialog.OnDateSetListener ondate2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            rdt.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(year));
        }
    };
}