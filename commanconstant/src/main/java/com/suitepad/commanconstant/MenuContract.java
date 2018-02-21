package com.suitepad.commanconstant;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class MenuContract {


    public static final String AUTHORITY = "com.suitepad.datasource";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MENU_ITEMS = "menu";


    public static final class MenuEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MENU_ITEMS).build();

        // Menu table and column names
        public static final String TABLE_NAME = "menus";

        // Since MenuEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the below

        public static final String COLUMN_UUID = "UUID";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_TYPE = "type";


    }


    public static final class MenuEntryIndex implements BaseColumns {

        public static final int COLUMN_ID = 0;
        public static final int COLUMN_UUID = 1;
        public static final int COLUMN_NAME = 2;
        public static final int COLUMN_PRICE = 3;
        public static final int COLUMN_TYPE = 4;

    }
}
