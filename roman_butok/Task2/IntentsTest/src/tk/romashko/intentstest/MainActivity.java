package tk.romashko.intentstest;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	private EditText emailText, subjText, bodyText;
	private RelativeLayout rlMain;
	private ImageView attachedImage;
	
	private Uri imageUri;
	
	private final String SEND_TO = "android.intent.action.SENDTO";
	private final String CHOOSER_TITLE = "Choose email app";
	private final int REQUEST_CODE = 0;
	private Class<ImageListActivity> imgActivityClass = 
			tk.romashko.intentstest.ImageListActivity.class;
			
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        emailText = (EditText) findViewById(R.id.email);
        subjText = (EditText) findViewById(R.id.subject);
        bodyText = (EditText) findViewById(R.id.text);
        rlMain = (RelativeLayout) findViewById(R.id.rl_main);
        final Button sendMail = (Button) findViewById(R.id.send_mail);
        final Button attachImage = (Button) findViewById(R.id.attach_image);
        
        sendMail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Sending email
				sendMail();
			}
		});
        
        attachImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				attachImage();				
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
    	String email = emailText.getText().toString();
    	String subject = subjText.getText().toString();
    	String body = bodyText.getText().toString();
    	
    	//Check if email field is empty
    	if (email.equals("")) {
    		emailText.setHint("You should enter email!");
    		emailText.setHintTextColor(Color.RED);
    	} else {
    		Uri uri = Uri.fromParts("mailto", email, null);
        	Intent intent = new Intent(SEND_TO, uri);
    		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    		intent.putExtra(Intent.EXTRA_TEXT, body);
    		
    		if (imageUri != null) {
    			intent.putExtra(Intent.EXTRA_STREAM, imageUri);
    		}
    		
    		startActivity(Intent.createChooser(intent, CHOOSER_TITLE));
    	}
    	
    }
    
    private void attachImage() {
    	Intent intent = new Intent(MainActivity.this, imgActivityClass);
    	startActivityForResult(intent, REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == REQUEST_CODE) {
    		if (resultCode == RESULT_OK) {
    			//Retrieve image id from data intent
    			imageUri = (Uri) data.getParcelableExtra(Intent.EXTRA_STREAM);
    			
    			//create ImageView with attachment
    			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(250, 250);
    			params.addRule(RelativeLayout.BELOW, R.id.send_mail);
    			attachedImage = new ImageView(MainActivity.this);
    			attachedImage.setLayoutParams(params);
    			attachedImage.setImageURI(imageUri);
    			attachedImage.setPadding(5, 5, 5, 5);
    			attachedImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    			
    			//add ImageView to RL
    			rlMain.addView(attachedImage);
    			
    		}
    	}
    }
    
}
