package com.example.hhTest;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {

		StringBuilder str = new StringBuilder();
		
		if(day<10) {
			str.append("0");
		};
		str.append(day).append(".");
		
		if(month+1<10) {
			str.append("0");
		};
		str.append(month+1).append(".");
		
		str.append(year);
		
		ApplicantActivity.tvBirthdate.setText(str);
	}
}