package com.example.secondtask.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.secondtask.app.data.ImageData;
import com.example.secondtask.app.text_wacther.MainActivityTextWatcher;

public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_IMAGE_KEY = "EXTRA_IMAGE_DATA";

    private EditText emailEditText;

    private EditText messageEditText;

    private ImageData imageData;

    private Button nextStepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        messageEditText = (EditText) findViewById(R.id.message_edit_text);
        nextStepButton = (Button) findViewById(R.id.next_step_button);
        imageData = new ImageData();
        emailEditText.addTextChangedListener(new MainActivityTextWatcher(emailEditText,
                messageEditText, nextStepButton));
        messageEditText.addTextChangedListener(new MainActivityTextWatcher(emailEditText,
                messageEditText, nextStepButton));

        final Intent nextStepIntent = new Intent(this, SelectImageActivity.class);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageData.setMessage(messageEditText.getText().toString());
                imageData.setEmail(emailEditText.getText().toString());
                nextStepIntent.putExtra(EXTRA_IMAGE_KEY, imageData);
                startActivity(nextStepIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
