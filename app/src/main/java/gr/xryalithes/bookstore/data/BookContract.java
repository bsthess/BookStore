package gr.xryalithes.bookstore.data;

import android.provider.BaseColumns;



public final class BookContract {

    // instatiate the needed empty constructor
    private BookContract() {
    }

    public static final class BookData implements BaseColumns {

        public final static String TABLE_NAME = "books";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_BOOK_NAME = "title";

        public final static String COLUMN_BOOK_PRICE = "price";

        public final static String COLUMN_BOOK_QUANTITY = "quantity";

        public final static String COLUMN_SUPPLIER_NAME = "supplier";

        public final static String COLUMN_SUPPLIER_PHONE = "supplier_phone";

    }

}
