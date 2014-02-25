package com.example.at02_activityresult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    Button btnBackground;
    TextView tvText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);		
		
		tvText = (TextView)findViewById(R.id.textView1);
		
		btnBackground = (Button)findViewById(R.id.button1);
		btnBackground.setOnClickListener(this);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	        int color = data.getIntExtra("color", Color.WHITE);
	        View view = this.getWindow().getDecorView();
	        view.setBackgroundColor(color);
	    } else {
	      Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, BackgroundActivity.class);
	    startActivityForResult(intent, 1);		
	}

}
