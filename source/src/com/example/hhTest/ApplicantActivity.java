package com.example.hhTest;

import com.example.hhTest.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ApplicantActivity extends Activity {

	EditText etFullName;
	public static TextView tvBirthdate;
	Spinner spSex;
	EditText etPosition;
	EditText etSalary;
	EditText etPhone;
	EditText etEmail;
	TextView tvEmptyFields;
	TextView tvEmptyFieldsCaption;
	TextView tvReply;
	LinearLayout llResume;
	LinearLayout llReply;
	
	Intent inIntent;
	Intent outIntent;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applicant);
		
		llReply = (LinearLayout)findViewById(R.id.llReply);
		llResume = (LinearLayout)findViewById(R.id.llResume);
		
		inIntent = getIntent();
		
		tvReply = (TextView) findViewById(R.id.tvReply);
		tvReply.setText(inIntent.getStringExtra("reply"));
		
		if(tvReply.getText().toString().equals("")) {
			llReply.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
		}
		else
		{
			llResume.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
		}
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spSex = (Spinner) findViewById(R.id.spSex);
        spSex.setAdapter(adapter);
        
        etFullName = (EditText) findViewById(R.id.etFullName);
        tvBirthdate = (TextView) findViewById(R.id.tvBirthdate);
        etPosition = (EditText) findViewById(R.id.etPosition);
        etSalary = (EditText) findViewById(R.id.etSalary);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        tvEmptyFields = (TextView) findViewById(R.id.tvEmptyFields);
        tvEmptyFieldsCaption = (TextView) findViewById(R.id.tvEmptyFieldsCaption);
	}

	public void sendResume(View v) {
		checkEmpty();
		
		if(tvEmptyFields.getText().toString().equals(""))
		{
			outIntent = new Intent(this, EmployerActivity.class);
			outIntent.putExtra("fullname", etFullName.getText().toString());
			outIntent.putExtra("birthdate", tvBirthdate.getText().toString());
			outIntent.putExtra("sex", spSex.getSelectedItem().toString());
			outIntent.putExtra("position", etPosition.getText().toString());
			outIntent.putExtra("salary", etSalary.getText().toString());
			outIntent.putExtra("phone", etPhone.getText().toString());
			outIntent.putExtra("email", etEmail.getText().toString());
			startActivity(outIntent);
		}
		else
		{
			tvEmptyFieldsCaption.setText(getString(R.string.empty_fields));
		}
	}
	
	public void showDateDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public void checkEmpty() {
		tvEmptyFields.setText("");
		if(etFullName.getText().toString().equals("")) {
			tvEmptyFields.setText(Html.fromHtml("<font color=\"red\"><u>" + getString(R.string.full_name) + "</u></font>"));
		}
		if(tvBirthdate.getText().toString().equals(getString(R.string.empty_birthdate))) {
			if(!tvEmptyFields.getText().toString().equals(""))
			{
				tvEmptyFields.append("\n");
			}
			tvEmptyFields.append(Html.fromHtml("<font color=\"red\"><u>" + getString(R.string.birthdate) + "</u></font>"));
		}
		if(etPosition.getText().toString().equals("")) {
			if(!tvEmptyFields.getText().toString().equals(""))
			{
				tvEmptyFields.append("\n");
			}
			tvEmptyFields.append(Html.fromHtml("<font color=\"red\"><u>" + getString(R.string.position) + "</u></font>"));
		}
		if(etSalary.getText().toString().equals("")) {
			if(!tvEmptyFields.getText().toString().equals(""))
			{
				tvEmptyFields.append("\n");
			}
			tvEmptyFields.append(Html.fromHtml("<font color=\"red\"><u>" + getString(R.string.salary) + "</u></font>"));
		}
		if(etPhone.getText().toString().equals("")) {
			if(!tvEmptyFields.getText().toString().equals(""))
			{
				tvEmptyFields.append("\n");
			}
			tvEmptyFields.append(Html.fromHtml("<font color=\"red\"><u>" + getString(R.string.phone) + "</u></font>"));
		}
		if(etEmail.getText().toString().equals("")) {
			if(!tvEmptyFields.getText().toString().equals(""))
			{
				tvEmptyFields.append("\n");
			}
			tvEmptyFields.append(Html.fromHtml("<font color=\"red\"><u>" + getString(R.string.email) + "</u></font>"));
		}
	}
}
