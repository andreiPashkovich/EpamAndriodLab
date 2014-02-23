package by.dolbik.tast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputNameActivity extends Activity implements OnClickListener {
	
	private EditText etInputName;
	private Button btnOk;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_name);
		
		etInputName = (EditText) findViewById(R.id.etInputName);
		
		btnOk = (Button) findViewById(R.id.btnOk);
		btnOk.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnOk:
				if (TextUtils.isEmpty(etInputName.getText().toString())) {
					Toast.makeText(this, "¬ведите им€", Toast.LENGTH_LONG).show();
				} else {
					Intent resultIntent = new Intent();
					resultIntent.putExtra("name", etInputName.getText().toString());
					setResult(RESULT_OK, resultIntent);
					finish();
				}
			break;
		}
		
	}

}
