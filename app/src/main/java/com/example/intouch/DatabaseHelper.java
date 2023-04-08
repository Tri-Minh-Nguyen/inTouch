    package com.example.intouch;

    import android.content.Context;
    import android.content.ContentValues;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "login_info";

        private static final String TABLE_NAME = "users";

        private static final String COLUMN_ID = "id";
        private static final String COLUMN_EMAIL = "email";
        private static final String COLUMN_PASSWORD = "password";

        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_EMAIL + " TEXT,"
                        + COLUMN_PASSWORD + " TEXT"
                        + ")";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        public void addUser(String email, String password) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_EMAIL, email);
            values.put(COLUMN_PASSWORD, password);

            db.insert(TABLE_NAME, null, values);

            db.close();
        }

        public boolean loginUser(String email, String password) {
            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {
                    COLUMN_EMAIL,
                    COLUMN_PASSWORD
            };

            String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {email, password};

            Cursor cursor = db.query(
                    TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            boolean success = (cursor.getCount() > 0);
            cursor.close();
            db.close();
            return success;
        }


    }
