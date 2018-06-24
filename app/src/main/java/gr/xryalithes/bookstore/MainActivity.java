package gr.xryalithes.bookstore;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import gr.xryalithes.bookstore.data.BookContract;
import gr.xryalithes.bookstore.data.BookContract.BookData;
import gr.xryalithes.bookstore.data.BookDbHelper;


public class MainActivity extends AppCompatActivity {

    ///DECLARE VIEWS FOR DISPLAY DATA/////////////////////////////////////
    TextView displayHeaderView;
    TextView displayNameView;
    TextView displayPriceView;
    TextView displayQuantityView;
    TextView displaySupplierNameView;
    TextView displaySupplierPhoneView;

    private BookDbHelper mDbHelper;//   the database handling class which will provide tha database instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////INITIALIZE VIEWS///////////////////////////////////////////////
        displayHeaderView = findViewById(R.id.header_text_view);
        displayNameView = findViewById(R.id.name_text_view);
        displayPriceView = findViewById(R.id.price_text_view);
        displayQuantityView = findViewById(R.id.quantity_text_view);
        displaySupplierNameView = findViewById(R.id.supplier_name_text_view);
        displaySupplierPhoneView = findViewById(R.id.supplier_phone_text_view);

        //CREATE A NEW HELPER CLASS FOR DATABASE HANDLING///////////////////////
        mDbHelper = new BookDbHelper(this);

        insertProducts();// INSERT BOOKS INTO THE DATABASE METHOD
        displayBookData();// A METHOD FOR DISPLAY THE INSERTED DATA ( FOR TEST AND DEBUGG PURPOSES)
    }

    private void insertProducts() {
        //
        SQLiteDatabase db = mDbHelper.getWritableDatabase();// GET A WRITABLE DATABASE

        ContentValues values = new ContentValues();// CREATE A VALUES OBJECT CONTAINING THE VALUES THAT WE WANT TO INSERT
        ContentValues values2 = new ContentValues();
        // PUT THE DATA INTO THE VALUES OBJECT TO THE CORRESPONDING COLUMN//////
        values.put(BookData.COLUMN_BOOK_NAME, "Robbin Hood");
        values.put(BookData.COLUMN_BOOK_PRICE, 15);
        values.put(BookData.COLUMN_BOOK_QUANTITY, 10);
        values.put(BookData.COLUMN_SUPPLIER_NAME, "Smith");
        values.put(BookData.COLUMN_SUPPLIER_PHONE, 24425);

        values2.put(BookData.COLUMN_BOOK_NAME, "The idiot");
        values2.put(BookData.COLUMN_BOOK_PRICE, 20);
        values2.put(BookData.COLUMN_BOOK_QUANTITY, 10);
        values2.put(BookData.COLUMN_SUPPLIER_NAME, "Cactus");
        values2.put(BookData.COLUMN_SUPPLIER_PHONE, 24410);

        ///FINALLY , WE INSERT THE VALUES OBJECTS INTO THE DATABASE'S TABLE NAME THAT WE WANT/////
        db.insert(BookData.TABLE_NAME, null, values);
        db.insert(BookData.TABLE_NAME, null, values2);
    }

    //METHOD THAT GETS THE DATA FROM THE DATABASE//////////////////
    private Cursor queryBookData() {
        //
        SQLiteDatabase db = mDbHelper.getReadableDatabase();//GET A READABLE DATABASE TO SHOW DATA
        String[] projection = {   // THIS STRING HAS ALL THE COLUMNS THAT WE WANT TO GET DATA FROM
                BookData._ID,
                BookContract.BookData.COLUMN_BOOK_NAME,
                BookData.COLUMN_BOOK_PRICE,
                BookData.COLUMN_BOOK_QUANTITY,
                BookData.COLUMN_SUPPLIER_NAME,
                BookData.COLUMN_SUPPLIER_PHONE

        };

        // Perform a query on the books table.Cursor is the object that holds the currently wanted data
        Cursor cursor = db.query(
                BookData.TABLE_NAME,   // The table to query
                projection,            // The columns to return,using the string projection
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        return cursor;
    }

    /// THE METHOD FOR DISPLAYING THE RETRIEVED DATA (FOR TESTING PURPOSE)
    @SuppressLint("SetTextI18n")
    public void displayBookData() {
        Cursor cursor = queryBookData();

        try {

            displayHeaderView.setText("BOOKS IN STORE: " + cursor.getCount() + " \n");
            displayNameView.setText(BookData.COLUMN_BOOK_NAME + "\n\n");
            displayPriceView.setText(BookData.COLUMN_BOOK_PRICE + "\n\n");
            displayQuantityView.setText(BookData.COLUMN_BOOK_QUANTITY + "\n\n");
            displaySupplierNameView.setText(BookData.COLUMN_SUPPLIER_NAME + "\n\n");
            displaySupplierPhoneView.setText(BookData.COLUMN_SUPPLIER_PHONE + "\n\n");


            // Figure out the index of each column

            int nameColumnIndex = cursor.getColumnIndex(BookData.COLUMN_BOOK_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookData.COLUMN_BOOK_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookData.COLUMN_BOOK_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookData.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(BookData.COLUMN_SUPPLIER_PHONE);

            while (cursor.moveToNext()) {
                ///getting the values from each column according the column index
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                long currentSupplierPhone = cursor.getLong(supplierPhoneColumnIndex);
                //display the values (for testing if ok)
                displayNameView.append(currentName + " \n");
                displayPriceView.append(currentPrice + " \n");
                displayQuantityView.append(currentQuantity + " \n");
                displaySupplierNameView.append(currentSupplierName + " \n");
                displaySupplierPhoneView.append(currentSupplierPhone + " \n");


            }
        } finally {

            cursor.close();
        }
    }


}