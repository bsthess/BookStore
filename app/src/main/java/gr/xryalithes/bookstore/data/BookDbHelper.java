package gr.xryalithes.bookstore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import gr.xryalithes.bookstore.data.BookContract.BookData;

// the class for managing the database creation and upgrade
public class BookDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "books.db";
    private static final int DATABASE_VERSION = 1;

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the books table
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BookData.TABLE_NAME + " ("
                + BookData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookData.COLUMN_BOOK_NAME + " TEXT NOT NULL, "
                + BookData.COLUMN_BOOK_PRICE + " INT NOT NULL, "
                + BookData.COLUMN_BOOK_QUANTITY + " INT, "
                + BookData.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + BookData.COLUMN_SUPPLIER_PHONE + " INT);";
        Log.v("create table command: ", SQL_CREATE_BOOKS_TABLE);

        // Execute the SQL statement that actually creates the table
        db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // When an upgrade needed, execute this
    }
}