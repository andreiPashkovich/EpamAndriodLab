package database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper {
    public static final String DB_NAME = "AndroidLab_task_two";
    public static final int DB_VERSION = 1;
    public static final String DB_TABLE = "all_users";

    public static String getDbTable() {
        return DB_TABLE;
    }

    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";

    private final Context mCtx;
    private DBHelper mDBHelper;

    private SQLiteDatabase mDB;

    public MyDBHelper(Context cont) {

        mCtx = cont;
    }

    // open a connection
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public SQLiteDatabase returnSQLiteDatabase() {
        return mDB;
    }

    // close a connection
    public void close() {
        if (mDBHelper != null)
            mDBHelper.close();
    }

    // get all the data from the table DB_TABLE
    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory,
                int version) {
            super(context, name, factory, version);
        }

        // Creates and populates the database
        @Override
        public void onCreate(SQLiteDatabase db) {
            // create a table with fields
            db.execSQL("create table all_users (" + USER_NAME + " TEXT,"
                    + USER_EMAIL + " TEXT primary key," + USER_PASSWORD
                    + " TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
