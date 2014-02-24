package com.epam.lecture2.interactionofactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {
	
	Button mCrossingButton;
	
	public String getResult(double number1, double number2, char operand) {
		double result = 0;
		switch(operand) {
			case '+':
				result = number1 + number2;
				break;
			case '-':
				result = number1 - number2;
				break;
			case '*':
				result = number1 * number2;
				break;
			case '/':
				try {
					if(number2 == 0.0) {
						throw new ArithmeticException();
					}
					result = number1 / number2;
				}
				catch(ArithmeticException e) {
					return "Division by 0";
				}
				break;
			default:
				return "Wrong operand";
				
		}
		return Double.valueOf(result).toString();
	}
	public boolean isIntentEmpty(Intent intent) {
		if(intent.hasExtra("number1") && intent.hasExtra("number2") && intent.hasExtra("operand")) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		
		final Intent intent = getIntent();
		mCrossingButton = (Button) findViewById(R.id.crossingTwo_button);
		mCrossingButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double number1 = 0.0;
				double number2 = 0.0;
				char operand = 0;
				Intent resultIntent = new Intent(SecondActivity.this, FirstActivity.class);
				if(!isIntentEmpty(intent)) {
					number1 = intent.getDoubleExtra("number1", -1);
					number2 = intent.getDoubleExtra("number2", -1);
					operand = intent.getCharExtra("operand", '.');
					resultIntent.putExtra("result", getResult(number1, number2, operand));
				}
				else {
					resultIntent.putExtra("result", "Incorrect input");
				}
				resultIntent.putExtra("number1", number1);
				resultIntent.putExtra("number2", number2);
				resultIntent.putExtra("operand", operand);
				startActivity(resultIntent);
				
				SecondActivity.this.finish();
			}
		});
	}
	
}
