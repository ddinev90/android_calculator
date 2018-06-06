package com.example.ddine.perimeterandarea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CalculatingShapeDB";
    public static final int DATABASE_VERSION = 6;

    private static final String TRIANGLE_TABLE_NAME = "triangle";
    private static final String TRIANGLE_COL_ID = "id";
    private static final String TRIANGLE_VALUE_A = "valueA";
    private static final String TRIANGLE_VALUE_B = "valueB";
    private static final String TRIANGLE_VALUE_C = "valueC";
    private static final String TRIANGLE_RESULT = "result";
    private static final String TRIANGLE_HISTORY_INFORMATION = "history";
    private static final String LOGIN_TABLE_NAME = "LOGIN";
    private static final String LOGIN_COL_ID = "ID";
    private static final String LOGIN_USERNAME = "USERNAME";
    private static final String LOGIN_PASSWORD = "PASSWORD";


    private static final String QUERY_LOGIN_CREATE_TABLE =
            "CREATE TABLE " + LOGIN_TABLE_NAME + " (" + LOGIN_COL_ID + " INTEGER PRIMARY KEY," +
                    LOGIN_USERNAME + " TEXT, " + LOGIN_PASSWORD + " TEXT" +  "  );";

    private static final String QUERY_TRIANGLE_CREATE_TABLE =
            "CREATE TABLE " + TRIANGLE_TABLE_NAME + " (" + TRIANGLE_COL_ID + " INTEGER PRIMARY KEY, " +
                    TRIANGLE_VALUE_A + " TEXT, " + TRIANGLE_VALUE_C + " TEXT, " + TRIANGLE_VALUE_B + " TEXT, " + TRIANGLE_RESULT + " TEXT," + TRIANGLE_HISTORY_INFORMATION + " TEXT" +  "  );";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase mReadableDB = getReadableDatabase();
        SQLiteDatabase mWritableDB = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("query", QUERY_TRIANGLE_CREATE_TABLE);
        db.execSQL(QUERY_TRIANGLE_CREATE_TABLE);
        Log.e("query", QUERY_LOGIN_CREATE_TABLE);
        db.execSQL(QUERY_LOGIN_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TRIANGLE_TABLE_NAME );
        onCreate(db);
    }

    public void insertTriangleCalc(String a,String b, String c,String d,String e){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRIANGLE_VALUE_A,a);
        values.put(TRIANGLE_VALUE_B,b);
        values.put(TRIANGLE_VALUE_C,c);
        values.put(TRIANGLE_RESULT,d);
        values.put(TRIANGLE_HISTORY_INFORMATION,e);

        database.insert("triangle", null, values);
        database.close();

    }
    public void insertEntry(String userName,String password)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
// Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);

// Insert the row into your table
        database.insert("LOGIN", null, newValues);
///Toast.makeText(context, “Reminder Is Successfully Saved”, Toast.LENGTH_LONG).show();
    }
    public List<Triangle> ObjectRead() {

        List<Triangle> valuesList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TRIANGLE_TABLE_NAME
                + " ORDER BY " + TRIANGLE_COL_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if ( cursor != null && cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String valueA = cursor.getString(cursor.getColumnIndex("valueA"));
                String valueB = cursor.getString(cursor.getColumnIndex("valueB"));
                String valueC = cursor.getString(cursor.getColumnIndex("valueC"));
                String result = cursor.getString(cursor.getColumnIndex("result"));
                String history = cursor.getString(cursor.getColumnIndex("history"));

                Triangle triangle = new Triangle();
                triangle.id = id;
                triangle.valueA = valueA;
                triangle.valueB  = valueB;
                triangle.valueC = valueC;
                triangle.Result = result;
                triangle.history = history;
                valuesList.add(triangle);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return valuesList;
    }
    public String getSingleEntry(String userName)
    {
        String selectQuery = "SELECT * FROM MyTable WHERE " + userName + " =?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public void updateEntry(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
// Define the updated row content.
        ContentValues updatedValues = new ContentValues();
// Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }
}
