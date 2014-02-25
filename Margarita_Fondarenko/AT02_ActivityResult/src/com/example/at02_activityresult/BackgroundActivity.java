package com.example.at02_activityresult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BackgroundActivity extends Activity implements OnClickListener {
	Button btnRed;
	Button btnBlue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.background_activity);
		
		btnRed = (Button)findViewById(R.id.btnRed);
		btnBlue = (Button)findViewById(R.id.btnBlue);
		
		btnRed.setOnClickListener(this);
		btnBlue.setOnClickListener(this);	
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();		
		switch (v.getId()) {
		case R.id.btnRed:
			intent.putExtra("color", Color.RED);
			break;
        case R.id.btnBlue:
        	intent.putExtra("color", Color.BLUE);
			break;	    
		}
		setResult(RESULT_OK, intent);
		finish();		
	}

}
