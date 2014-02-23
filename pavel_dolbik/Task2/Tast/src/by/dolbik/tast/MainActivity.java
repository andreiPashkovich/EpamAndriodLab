package by.dolbik.tast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button btnEmail;
	private Button btnName;
	private TextView tvName;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);
        
        btnName = (Button) findViewById(R.id.btnName);
        btnName.setOnClickListener(this);
        
        tvName = (TextView) findViewById(R.id.tvName);
    }



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnEmail:
				attachFile();
				Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"siarhei_ivonchik@epam.com"});       
                email.putExtra(Intent.EXTRA_SUBJECT, "Task №2");
                email.putExtra(Intent.EXTRA_TEXT, "Plain text!");
                email.setType("text/html");
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png"));
                email.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(email, "Выберите почтовую программу:"));	
			break;
			
			case R.id.btnName:
				Intent nameIntent = new Intent(this, InputNameActivity.class);
				startActivityForResult(nameIntent, 1);
			break;
		}
		
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null) {
			return;
		}
		
		if(resultCode == RESULT_OK) {
			String name = data.getStringExtra("name");
			tvName.setText("Приятно познакомиться "+name);
		}
	}

	
	
	private void attachFile() {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = getResources().openRawResource(R.drawable.ic_launcher);
			out = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "image.png"));
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ( (read = in.read(buffer)) != -1 ) {
			out.write(buffer, 0, read);
		}
	}
   
}
