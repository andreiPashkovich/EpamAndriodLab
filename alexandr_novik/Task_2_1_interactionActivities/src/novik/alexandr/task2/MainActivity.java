package novik.alexandr.task2;

import java.util.ArrayList;
import java.util.List;

import novik.alexandr.task_2_1.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import database.MyDBHelper;
import entity.User;

/**
 * @author Alexandr_Novik
 * @version 0.1 The authorization system
 */
public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG_LOG = "myLogs";
    private EditText etEmail;
    private EditText etPassword;
    private Button logIn;
    private Button registration;
    private MyDBHelper dbHelper;
    private Cursor cursor;

    private List<User> arrayList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        etEmail = (EditText) findViewById(R.id.editText1);
        etPassword = (EditText) findViewById(R.id.editText2);
        logIn = (Button) findViewById(R.id.button_logIn);
        registration = (Button) findViewById(R.id.button_Registratoin);

        logIn.setOnClickListener(this);
        registration.setOnClickListener(this);

        putDatabaseInArrayList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_logIn:
            String email = etEmail.getText().toString();
            String pass = etPassword.getText().toString();
            String name;
            boolean check = false;
            for (int i = 0; i < arrayList.size(); i++) {
                if ((arrayList.get(i).email).equals(email)
                        && (arrayList.get(i).password).equals(pass)) {
                    name = arrayList.get(i).name;
                    Intent intent = new Intent(getApplicationContext(),
                            LogInActivity.class).putExtra("name", name);
                    startActivity(intent);
                    check = true;
                }
            }
            if (!check) {
                Toast.makeText(getApplicationContext(),
                        "this combination does not exist", Toast.LENGTH_SHORT)
                        .show();
            }
            clearFilds();
            break;
        case R.id.button_Registratoin:
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
            finish();
            break;

        default:
            break;
        }
    }

    private void clearFilds() {
        etEmail.setText("");
        etPassword.setText("");
    }

    private void putDatabaseInArrayList() {
        arrayList.clear();
        // open DB
        dbHelper = new MyDBHelper(this);
        dbHelper.open();
        // get cursor
        try {
            cursor = dbHelper.getAllData();
        } catch (Exception exception) {
        }
        // put DB in ArrayList
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String str;
                do {
                    str = "";
                    arrayList
                            .add(new User(
                                    str.concat(cursor.getString(cursor
                                            .getColumnIndex(MyDBHelper.USER_NAME))),
                                    str.concat(cursor.getString(cursor
                                            .getColumnIndex(MyDBHelper.USER_EMAIL))),
                                    str.concat(cursor.getString(cursor
                                            .getColumnIndex(MyDBHelper.USER_PASSWORD)))));

                } while (cursor.moveToNext());
            }
            cursor.close();
        } else {
            Log.d(TAG_LOG, "Cursor is null");
        }
    }
}
