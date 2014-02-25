package com.example.task2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

private EditText sendToText, subjText, descText;

private final String SEND_TO = "android.intent.action.SendTo";
private final String CHOOSER_TITLE = "Select mail client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sendToText = (EditText) findViewById(R.id.email);
        subjText = (EditText) findViewById(R.id.subject);
        descText = (EditText) findViewById(R.id.description);
        final Button sendMail = (Button) findViewById(R.id.send_mail);
        
        sendMail.setOnClickListener(new OnClickListener() {

     @Override
     public void onClick(View v) {
     //Send mail
     sendMail();
     }
     });     
      
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
    private void sendMail() {
     String email = sendToText.getText().toString();
     String subject = subjText.getText().toString();
     String description = descText.getText().toString();
    
     //Check if email field is empty
     if (email.equals("")) {
     sendToText.setHint("Please, enter valid email address");
     sendToText.setHintTextColor(Color.RED);
     } else {
     Uri uri = Uri.fromParts("mailto", email, null);
     Intent intent = new Intent(SEND_TO, uri);
     intent.putExtra(Intent.EXTRA_SUBJECT, subject);
     intent.putExtra(Intent.EXTRA_TEXT, description);
    
     startActivity(Intent.createChooser(intent, CHOOSER_TITLE));
     }
    
    }
  
}