package by.stanislav.intentemail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button attachmentBtn;
	private Button instagram;
	private Button sendEmail;
	private Button twitter;
	private Button skype;
	private Button call;
	private Button sms;

	private EditText inputEmailSubject;
	private EditText inputEmailMessage;
	private EditText inputSmsText;
	
	private TextView fileName;
	
	private String attachmentFile;
	private String textForSms;	

	private int columnIndex;

	private Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		twitter = (Button) findViewById(R.id.twitter);
		instagram = (Button) findViewById(R.id.instagram);
		skype = (Button) findViewById(R.id.skype);
		call = (Button) findViewById(R.id.call);
		sms = (Button) findViewById(R.id.sms);
		sendEmail = (Button) findViewById(R.id.sendEmail);
		attachmentBtn = (Button) findViewById(R.id.attachFile);
		inputEmailSubject = (EditText) findViewById(R.id.editSubject);
		inputEmailMessage = (EditText) findViewById(R.id.editMessage);
		fileName = (TextView) findViewById(R.id.fileName);
		twitter.setOnClickListener(this);
		instagram.setOnClickListener(this);
		skype.setOnClickListener(this);
		call.setOnClickListener(this);
		sms.setOnClickListener(this);
		sendEmail.setOnClickListener(this);
		attachmentBtn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.twitter:
			Helper.openTwitter(this, Constants.LOGIN);
			break;

		case R.id.instagram:
			Helper.openInstagram(this, Constants.LOGIN);
			break;

		case R.id.skype:
			Helper.openSkype(this, Constants.LOGIN);
			break;

		case R.id.call:
			Helper.makeCall(this, Constants.TELNUMBER);
			break;

		case R.id.sms:
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setMessage("Text message");
			inputSmsText = new EditText(this);
			alert.setView(inputSmsText);
			alert.setPositiveButton("OK", okButton);
			alert.setNegativeButton("Cancel", cancelButton);
			alert.show();
			break;
		case R.id.sendEmail:
			sendEmail(inputEmailSubject.getText().toString(), inputEmailMessage
					.getText().toString());
			break;
		case R.id.attachFile:
			openGallery();
			break;
		}

	}

	DialogInterface.OnClickListener okButton = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			textForSms = inputSmsText.getText().toString();
			Helper.sendSMS(MainActivity.this, textForSms);
			dialog.cancel();
		}
	};

	DialogInterface.OnClickListener cancelButton = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.PICK_FROM_GALLERY
				&& resultCode == RESULT_OK) {
			/**
			 * Get Path
			 */
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
			columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			attachmentFile = cursor.getString(columnIndex);
			uri = Uri.parse("file://" + attachmentFile);
			fileName.setText(attachmentFile);
			
			cursor.close();
		}
	}

	private void openGallery() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.putExtra("return-data", true);
		startActivityForResult(Intent.createChooser(intent, "Choose file"),
				Constants.PICK_FROM_GALLERY);

	}

	private void sendEmail(String subject, String message) {
		try {
			Intent gmailIntent = new Intent(Intent.ACTION_SEND);
			gmailIntent.putExtra(Intent.EXTRA_EMAIL, Constants.EMAIL_ADDRESSES);
			gmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			gmailIntent.putExtra(Intent.EXTRA_TEXT, message);
			gmailIntent.setType("plain/text");
			gmailIntent.putExtra(Intent.EXTRA_STREAM, uri);
			startActivity(Intent.createChooser(gmailIntent, "Email client:"));
		} catch (Throwable t) {
			Helper.showToast(this, "Can not send email");
		}
	}

}
