package com.epam.lecture2.interactionofactivities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends Activity {
	
	EditText mNumber1EditText;
	EditText mNumber2EditText;
	EditText mOperandEditText;
	Button mCrossingButton;
	TextView mResultTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.first_activity);	
		mNumber1EditText = (EditText) findViewById(R.id.number1_edittext);
		mNumber2EditText = (EditText) findViewById(R.id.number2_edittext);
		mOperandEditText = (EditText) findViewById(R.id.operand_edittext);
		mResultTextView = (TextView) findViewById(R.id.result_textview);
		
		mCrossingButton = (Button) findViewById(R.id.crossingOne_button);
		mCrossingButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
				intent.putExtra("number1", Double.valueOf(mNumber1EditText.getText().toString()));
				intent.putExtra("number2", Double.valueOf(mNumber2EditText.getText().toString()));
				intent.putExtra("operand", mOperandEditText.getText().toString().charAt(0));
				
				startActivity(intent);	
				FirstActivity.this.finish();
			}
		});
		Intent resultIntent = getIntent();
		if(resultIntent.hasExtra("result")) {
			mNumber1EditText.setText(Double.valueOf(resultIntent.getDoubleExtra("number1", -1)).toString());
			mNumber2EditText.setText(Double.valueOf(resultIntent.getDoubleExtra("number2", -1)).toString());
			mOperandEditText.setText(String.valueOf(resultIntent.getCharExtra("operand", '.')));
			StringBuffer sb = new StringBuffer(mResultTextView.getText());
			mResultTextView.setText(sb.append(' ').append(resultIntent.getStringExtra("result")));
		}

	}

}
