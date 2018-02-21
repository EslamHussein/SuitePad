package com.suitepad.datasource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class DataSourceContentProvider extends ContentProvider {


    public static final int MENU = 100;

    public static final UriMatcher sUriMatcher = buildUriMatcher();


    public static UriMatcher buildUriMatcher() {


        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MenuContract.AUTHORITY, MenuContract.PATH_MENU_ITEMS, MENU);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {


        switch (sUriMatcher.match(uri)) {
            case MENU:
                return getMenuData();
            default:
                throw new UnsupportedOperationException("Unsupported Uri");

        }


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        throw new UnsupportedOperationException("Not implemented Yet");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented Yet");

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented Yet");
    }


    private MatrixCursor getMenuData() {


        MatrixCursor menuItemsCursor = new MatrixCursor(new String[]{MenuContract.MenuEntry._ID,
                MenuContract.MenuEntry.COLUMN_UUID,
                MenuContract.MenuEntry.COLUMN_NAME,
                MenuContract.MenuEntry.COLUMN_PRICE,
                MenuContract.MenuEntry.COLUMN_TYPE});


        Object[] row = new Object[5];


        ArrayList<MenuItem> dataSource = MenuDataSource.getMenusItem();
        for (int i = 0; i < dataSource.size(); i++) {

            MenuItem menuItem = dataSource.get(i);
            row[0] = i + 1;
            row[1] = menuItem.getUUID();
            row[2] = menuItem.getName();
            row[3] = menuItem.getPrice();
            row[4] = menuItem.getType();
            menuItemsCursor.addRow(row);
        }


        return menuItemsCursor;
    }
}
