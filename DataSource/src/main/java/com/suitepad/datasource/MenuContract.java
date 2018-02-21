package com.suitepad.datasource;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class MenuContract {


    static final String AUTHORITY = "com.suitepad.datasource";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    static final String PATH_MENU_ITEMS = "menu";


    public static final class MenuEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MENU_ITEMS).build();

        // Menu table and column names
        public static final String TABLE_NAME = "menus";

        // Since MenuEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the below

        static final String COLUMN_UUID = "UUID";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_PRICE = "price";
        static final String COLUMN_TYPE = "type";


    }
}
