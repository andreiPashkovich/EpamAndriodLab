package com.example.task2_email;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends Activity {


	private static final int PICK_IMAGE = 1;
	private static Uri uri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
    public void selectPicture(View view){
    	Intent photoPickerIntent = new Intent(
    			Intent.ACTION_PICK,
    			android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    	startActivityForResult(photoPickerIntent, PICK_IMAGE);
    	
    }
    public void sendMessage(View view){
    	EditText EditTextAddr = (EditText) findViewById(R.id.editText1); 
    	EditText EditTextMsg = (EditText) findViewById(R.id.editText2);
    	String emailAddr = EditTextAddr.getText().toString();
    	String emailMsg = EditTextMsg.getText().toString();
    	Intent emailSenderIntent = new Intent(Intent.ACTION_SEND);
    	
    	emailSenderIntent.setType("plain/text");
    	emailSenderIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailAddr});
    	emailSenderIntent.putExtra(Intent.EXTRA_TEXT, emailMsg);
    	emailSenderIntent.putExtra(Intent.EXTRA_SUBJECT, "Epam task");
    	emailSenderIntent.putExtra(Intent.EXTRA_STREAM, uri);
    	
    	emailSenderIntent.setType("image/*");
    	startActivity(emailSenderIntent);
    	    	
    }
    
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            uri = data.getData();
            ImageView img = (ImageView) findViewById(R.id.imageView1);
            img.setImageURI(uri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}