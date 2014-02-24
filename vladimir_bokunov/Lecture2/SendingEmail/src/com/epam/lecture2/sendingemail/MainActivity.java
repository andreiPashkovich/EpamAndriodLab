package com.epam.lecture2.sendingemail;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	public static final int RESULT_GALLERY = 0;
	private Uri file;
	
	EditText toEditText;
	EditText subjectEditText;
	EditText contentEditText;
	Button sendingButton;
	Button additionButton;
	TextView statementTextView;
	
	public boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if(netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		else return false;	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		toEditText = (EditText) findViewById(R.id.to_edittext);
		subjectEditText = (EditText) findViewById(R.id.subject_edittext);
		contentEditText = (EditText) findViewById(R.id.content_edittext);
		sendingButton = (Button) findViewById(R.id.sending_button);
		sendingButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isOnline(MainActivity.this)) {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("plain/text");
					intent.putExtra(Intent.EXTRA_EMAIL, new String[] { toEditText.getText().toString() });
					intent.putExtra(Intent.EXTRA_SUBJECT, subjectEditText.getText().toString());
					intent.putExtra(Intent.EXTRA_TEXT, contentEditText.getText().toString());
					if(file != null) {
						intent.putExtra(Intent.EXTRA_STREAM, file);
						intent.setType("text/picture");
					}
					startActivity(intent);
				}
				else {
					Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
				}
			}
		});
		additionButton = (Button) findViewById(R.id.addition_button);
		additionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, RESULT_GALLERY);				
			}
		});
		statementTextView = (TextView) findViewById(R.id.statemant_textview);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == RESULT_GALLERY) {
			if(data != null) {
				file = data.getData();
				statementTextView.setText("File is added");
			}
		}
	}



}
