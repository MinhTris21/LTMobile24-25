package com.example.studentinfo;
//CAC THU VIEN DUOC SU DUNG DE THIET LAP CO SO DU LIEU SQLITE
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentInfo.db";
    private static final int DATABASE_VERSION = 2; // Incremented from 1 to 2
    private static final String TABLE_NAME = "students";
    private static final String COL_ID = "_id";
    private static final String COL_FULL_NAME = "full_name";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_PHONE = "phone";
    private static final String COL_EMAIL = "email";
    private static final String COL_AVATAR = "avatar";
    private static final String COL_GENDER = "gender";
    private static final String COL_YEAR = "year";

    public StudentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FULL_NAME + " TEXT, " +
                COL_STUDENT_ID + " TEXT, " +
                COL_PHONE + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_AVATAR + " TEXT, " +
                COL_GENDER + " TEXT, " +
                COL_YEAR + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COL_GENDER + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COL_YEAR + " TEXT");
        }
    }

    public boolean insertStudent(String fullName, String studentId, String phone, String email, String avatar, String gender, String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FULL_NAME, fullName);
        values.put(COL_STUDENT_ID, studentId);
        values.put(COL_PHONE, phone);
        values.put(COL_EMAIL, email);
        values.put(COL_AVATAR, avatar);
        values.put(COL_GENDER, gender);
        values.put(COL_YEAR, year);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getStudentById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=?", new String[]{String.valueOf(id)});
    }
}