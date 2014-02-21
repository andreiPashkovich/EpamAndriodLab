package novik.alexandr.task2;

import java.util.ArrayList;
import java.util.List;

import novik.alexandr.task_2_1.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import database.MyDBHelper;
import entity.User;

public class RegistrationActivity extends Activity implements OnClickListener {
    private EditText name;
    private EditText email;
    private EditText password;
    private Button ok;
    private Button cansel;
    private Cursor cursor;
    private MyDBHelper dbHelper = new MyDBHelper(this);
    private List<User> arrayList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Edit text filds
        name = (EditText) findViewById(R.id.editText_registration_name);
        email = (EditText) findViewById(R.id.editText_registration_email);
        password = (EditText) findViewById(R.id.editText_registration_password);
        // Buttons
        ok = (Button) findViewById(R.id.button_registration_ok);
        cansel = (Button) findViewById(R.id.button_registration_cansel);
        ok.setOnClickListener(this);
        cansel.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        // it is necessary to rebild main activity, for refresh DB
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_registration_ok:
            // if filds not empty, put in DB
            boolean flag = true;
            if (!(name.getText().toString()).equals("")
                    && !(email.getText().toString()).equals("")
                    && !(password.getText().toString()).equals("")) {
                MainActivity
                        .putDatabaseInArrayList(arrayList, dbHelper, cursor);
                // email is a primary key, if it exist -> show toast
                for (int i = 0; i < arrayList.size(); i++) {
                    if ((arrayList.get(i).email).equals(email.getText()
                            .toString())) {
                        Toast.makeText(getApplicationContext(),
                                "this email is already exists",
                                Toast.LENGTH_SHORT).show();
                        flag = false;
                    }
                }

                putInDatabase();
                clearFilds();
                if (flag)
                    Toast.makeText(getApplicationContext(), "successful",
                            Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Enter correct filds",
                        Toast.LENGTH_SHORT).show();
            }

            break;
        case R.id.button_registration_cansel:
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            break;
        default:
            break;
        }

    }

    // clearing fields
    private void clearFilds() {
        name.setText("");
        email.setText("");
        password.setText("");

    }

    /**
     * Add values to Database from EditText views
     */
    private void putInDatabase() {
        String putName = (String) name.getText().toString();
        String putEmail = (String) email.getText().toString();
        String putPassword = (String) password.getText().toString();
        SQLiteDatabase database;
        dbHelper.open();
        database = dbHelper.returnSQLiteDatabase();
        putCredentialsIntoDatabase(database, putName, putEmail, putPassword);

    }

    /**
     * Puts credentials to the database
     * 
     * @param database
     * @param putName
     * @param putEmail
     * @param putPassword
     */
    private void putCredentialsIntoDatabase(SQLiteDatabase database,
            String putName, String putEmail, String putPassword) {
        ContentValues values;
        values = new ContentValues();
        values.put(MyDBHelper.USER_NAME, putName);
        values.put(MyDBHelper.USER_EMAIL, putEmail);
        values.put(MyDBHelper.USER_PASSWORD, putPassword);
        database = dbHelper.returnSQLiteDatabase();
        database.insert(MyDBHelper.getDbTable(), null, values);
        dbHelper.close();

    }
}
