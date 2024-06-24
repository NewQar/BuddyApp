package com.example.csc557buddyapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "myfriends";
    private static final String TABLE_FRIEND = "friend";
    private static final String KEY_ID = "id";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_PHONENUM = "phonenum";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";


    SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_FRIEND + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NICKNAME + " TEXT, "
                + KEY_FULLNAME + " TEXT, "
                + KEY_PHONENUM + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_GENDER + " TEXT, "
                + KEY_DOB + " TEXT" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND);
        onCreate(db);
    }

    public long addFriend(String nickname, String fullname, String phonenum, String email, String gender, String dob) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NICKNAME, nickname);
        values.put(KEY_FULLNAME, fullname);
        values.put(KEY_PHONENUM, phonenum);
        values.put(KEY_EMAIL, email);
        values.put(KEY_GENDER, gender);
        values.put(KEY_DOB, dob);

        return db.insert(TABLE_FRIEND, null, values);
    }


    public String getFriend() {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIEND, new String[]{KEY_ID, KEY_NICKNAME, KEY_FULLNAME, KEY_PHONENUM, KEY_EMAIL, KEY_GENDER, KEY_DOB},
                null, null, null, null, null);

        int eId = cursor.getColumnIndex(KEY_ID);
        int eNickname = cursor.getColumnIndex(KEY_NICKNAME);
        int eFullname = cursor.getColumnIndex(KEY_FULLNAME);
        int ePhonenum = cursor.getColumnIndex(KEY_PHONENUM);
        int eEmail = cursor.getColumnIndex(KEY_EMAIL);
        int eGender = cursor.getColumnIndex(KEY_GENDER);
        int eDob = cursor.getColumnIndex(KEY_DOB);

        StringBuilder res = new StringBuilder();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            res.append("Id: ").append(cursor.getString(eId)).append("\n")
                    .append("Nickname: ").append(cursor.getString(eNickname)).append("\n")
                    .append("Full Name: ").append(cursor.getString(eFullname)).append("\n")
                    .append("Phone No.: ").append(cursor.getString(ePhonenum)).append("\n")
                    .append("Email: ").append(cursor.getString(eEmail)).append("\n")
                    .append("Gender: ").append(cursor.getString(eGender)).append("\n")
                    .append("Date of Birth: ").append(cursor.getString(eDob)).append("\n\n");
        }

        db.close();
        return res.toString();
    }

    public void updateFriend(long l, String nickname, String fullname, String phonenum, String email) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NICKNAME, nickname);
        values.put(KEY_FULLNAME, fullname);
        values.put(KEY_PHONENUM, phonenum);
        values.put(KEY_EMAIL, email);

        db.update(TABLE_FRIEND, values, KEY_ID + "= " + l, null);
        db.close();
    }

    public void deleteFriend(long l) {
        db = this.getWritableDatabase();
        db.delete(TABLE_FRIEND, KEY_ID + " =" + l, null);
        db.close();
    }

    public String getNickname(long l1) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIEND, new String[]{KEY_ID, KEY_NICKNAME, KEY_FULLNAME, KEY_PHONENUM, KEY_EMAIL, KEY_GENDER, KEY_DOB},
                KEY_ID + "=" + l1, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(1);
        }
        return null;
    }

    public String getFullname(long l1) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIEND, new String[]{KEY_ID, KEY_NICKNAME, KEY_FULLNAME, KEY_PHONENUM, KEY_EMAIL, KEY_GENDER, KEY_DOB},
                KEY_ID + "=" + l1, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(2);
        }
        return null;
    }

    public String getPhonenum(long l1) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIEND, new String[]{KEY_ID, KEY_NICKNAME, KEY_FULLNAME, KEY_PHONENUM, KEY_EMAIL, KEY_GENDER , KEY_DOB},
                KEY_ID + "=" + l1, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(3);
        }
        return null;
    }

    public String getEmail(long l1) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIEND, new String[]{KEY_ID, KEY_NICKNAME, KEY_FULLNAME, KEY_PHONENUM, KEY_EMAIL, KEY_GENDER, KEY_DOB},
                KEY_ID + "=" + l1, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(4);
        }
        return null;
    }

    public String getGender(long l1) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIEND, new String[]{KEY_ID, KEY_NICKNAME, KEY_FULLNAME, KEY_PHONENUM, KEY_EMAIL, KEY_GENDER, KEY_DOB},
                KEY_ID + "=" + l1, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(5);
        }
        return null;
    }

    public String getDOB(long friendId) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIEND, new String[]{KEY_ID, KEY_NICKNAME, KEY_FULLNAME, KEY_PHONENUM, KEY_EMAIL, KEY_GENDER, KEY_DOB},
                KEY_ID + "=" + friendId, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(6);
        }
        return null;
    }

    public int getGenderCount(String gender) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_FRIEND + " WHERE " + KEY_GENDER + "=?", new String[]{gender});

        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }

        return count;
    }

    public int[] getBirthMonthCount() {
        db = this.getReadableDatabase();
        int[] countArray = new int[12]; // Assuming 12 months

        Cursor cursor = db.rawQuery("SELECT " + KEY_DOB + ", COUNT(*) FROM " + TABLE_FRIEND + " GROUP BY " + KEY_DOB, null);

        if (cursor != null) {
            int dobIndex = cursor.getColumnIndex(KEY_DOB);
            int countIndex = cursor.getColumnIndex("COUNT(*)");

            while (cursor.moveToNext()) {
                if (dobIndex != -1 && countIndex != -1) {
                    try {
                        String dob = cursor.getString(dobIndex);
                        int monthValue = Integer.parseInt(dob.split("-")[1]) - 1; // Extract the month part and convert to 0-based index
                        int count = cursor.getInt(countIndex);
                        countArray[monthValue] += count;
                        Log.d("BirthMonthCount", "Month: " + (monthValue + 1) + ", Count: " + count); // Log values
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        // Handle the exception, for example, log it
                        e.printStackTrace();
                    }
                }
            }

            cursor.close();
        }

        return countArray;
    }





}
