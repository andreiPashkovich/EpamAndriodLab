package novik.alexandr.task_2_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LogInActivity extends Activity {

    TextView view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);
        view = (TextView) findViewById(R.id.textViewLogInNAME);
        Intent intent = getIntent();
        view.setText(intent.getStringExtra("name"));
        back = (Button) findViewById(R.id.Back);
        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
