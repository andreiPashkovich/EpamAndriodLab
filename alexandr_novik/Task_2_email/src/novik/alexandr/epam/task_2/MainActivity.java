package novik.alexandr.epam.task_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Alexandr Novik
 * @version 0.1 Utility program to send mail with pictures and videos
 */
public class MainActivity extends Activity {

    EditText edittextEmailAddress, edittextEmailSubject, edittextEmailText;
    TextView textPath;
    Button buttonSelectImage, buttonSendEmail_intent, buttonSelectVideo;

    final int RQS_LOAD = 0;

    Uri dataUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittextEmailAddress = (EditText) findViewById(R.id.email_address);
        edittextEmailSubject = (EditText) findViewById(R.id.email_subject);
        edittextEmailText = (EditText) findViewById(R.id.email_text);

        textPath = (TextView) findViewById(R.id.imagepath);

        buttonSelectImage = (Button) findViewById(R.id.selectimage);
        buttonSendEmail_intent = (Button) findViewById(R.id.sendemail_intent);
        buttonSelectVideo = (Button) findViewById(R.id.selectvideo);

        buttonSelectVideo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RQS_LOAD);

            }
        });

        buttonSelectImage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RQS_LOAD);

            }
        });

        buttonSendEmail_intent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String emailAddress = edittextEmailAddress.getText().toString();
                String emailSubject = edittextEmailSubject.getText().toString();
                String emailText = edittextEmailText.getText().toString();
                String emailAddressList[] = { emailAddress };

                Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);

                sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddressList);
                sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                sendEmailIntent.putExtra(Intent.EXTRA_TEXT, emailText);

                if (dataUri != null) {
                    sendEmailIntent.putExtra(Intent.EXTRA_STREAM, dataUri);
                    sendEmailIntent.setType("image/video");
                } else {
                    sendEmailIntent.setType("plain/text");
                }

                startActivity(Intent.createChooser(sendEmailIntent,
                        "Choice App to send email:"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
            case RQS_LOAD:
                dataUri = data.getData();
                textPath.setText(dataUri.toString());
                break;
            default:
                break;
            }
        }
    }
}
