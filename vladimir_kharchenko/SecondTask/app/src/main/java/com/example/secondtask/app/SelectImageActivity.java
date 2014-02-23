package com.example.secondtask.app;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.secondtask.app.data.ImageData;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SelectImageActivity extends ActionBarActivity {

    private ImageView imageView;

    private Button selectImageButton;

    private Button sendButton;

    private ImageData imageData;

    private static final String IMAGE_INTENT_TYPE = "image/*";

    private static final int SELECT_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);
        imageView = (ImageView) findViewById(R.id.image_view);
        selectImageButton = (Button) findViewById(R.id.select_image_button);
        sendButton = (Button) findViewById(R.id.send_button);
        imageData = (ImageData) getIntent().getExtras().get(MainActivity
                .EXTRA_IMAGE_KEY);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imageIntent = new Intent();
                imageIntent.setType(IMAGE_INTENT_TYPE);
                imageIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(imageIntent, "Select Image"),
                        SELECT_IMAGE);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, imageData.getEmail());
                emailIntent.putExtra(Intent.EXTRA_TEXT, imageData.getMessage());
                emailIntent.putExtra(Intent.EXTRA_STREAM, imageData.getImageUri());
                emailIntent.setType(IMAGE_INTENT_TYPE);
                startActivity(emailIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_picture, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
                imageData.setImageUri(selectedImageUri);
                sendButton.setEnabled(true);
            }
        }
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