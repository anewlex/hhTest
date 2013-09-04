package com.example.hhTest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.TextView;

public class EmployerActivity extends Activity implements OnTouchListener {

	TextView tvFullName;
	TextView tvBirthdate;
	TextView tvSex;
	TextView tvPosition;
	TextView tvSalary;
	TextView tvPhone;
	TextView tvEmail;
	EditText etReply;
	TextView tvEmptyFields;
	TextView tvEmptyFieldsCaption;
	
	Intent inIntent;
	Intent outIntent;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employer);
	
		tvFullName = (TextView) findViewById(R.id.tvFullName);
		tvBirthdate = (TextView) findViewById(R.id.tvBirthdate);
		tvSex = (TextView) findViewById(R.id.tvSex);
		tvPosition = (TextView) findViewById(R.id.tvPosition);
		tvSalary = (TextView) findViewById(R.id.tvSalary);
		tvPhone = (TextView) findViewById(R.id.tvPhone);
		tvEmail = (TextView) findViewById(R.id.tvEmail);
		etReply = (EditText) findViewById(R.id.etReply);
        tvEmptyFields = (TextView) findViewById(R.id.tvEmptyFields);
        tvEmptyFieldsCaption = (TextView) findViewById(R.id.tvEmptyFieldsCaption);
		
        etReply.setOnTouchListener(this);
		
		inIntent = getIntent();
	
		tvFullName.setText(inIntent.getStringExtra("fullname"));
		tvBirthdate.setText(inIntent.getStringExtra("birthdate"));
		tvSex.setText(inIntent.getStringExtra("sex"));
		tvPosition.setText(inIntent.getStringExtra("position"));
		tvSalary.setText(inIntent.getStringExtra("salary"));
		tvPhone.setText(Html.fromHtml("<u>" + inIntent.getStringExtra("phone") + "</u>"));
		tvEmail.setText(Html.fromHtml("<u>" + inIntent.getStringExtra("email") + "</u>"));
	}
	
	@Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view.getId() == R.id.etReply){
            view.getParent().requestDisallowInterceptTouchEvent(true);
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
        }
        return false;
    }
	
	public void sendReply(View v) {
		checkEmpty();
		
		if(tvEmptyFields.getText().toString().equals(""))
		{
			outIntent = new Intent(this, ApplicantActivity.class); 
			outIntent.putExtra("reply", etReply.getText().toString());
			startActivity(outIntent);
		}
		else
		{
			tvEmptyFieldsCaption.setText(getString(R.string.empty_fields));
		}
	}
	
	public void callPhone(View v) {
		outIntent = new Intent(); 
		outIntent.setAction(Intent.ACTION_CALL);
		outIntent.setData(Uri.parse("tel:" + tvPhone.getText().toString()));
    	startActivity(outIntent);
	}
	
	public void sendEmail(View v) {
		outIntent = new Intent(); 
		outIntent.setAction(Intent.ACTION_SENDTO);
		outIntent.setData(Uri.parse("mailto:" + tvEmail.getText().toString()));
    	startActivity(outIntent);
	}
	
	public void checkEmpty() {
		tvEmptyFields.setText("");
		if(etReply.getText().toString().equals("")) {
			tvEmptyFields.setText(Html.fromHtml("<font color=\"red\"><u>" + getString(R.string.reply_to_applicant) + "</u></font>"));
		}
	}
}
