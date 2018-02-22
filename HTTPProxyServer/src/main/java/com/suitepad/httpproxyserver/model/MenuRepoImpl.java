package com.suitepad.httpproxyserver.model;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.suitepad.commanconstant.MenuContract;
import com.suitepad.commanconstant.MenuItem;
import com.suitepad.httpproxyserver.App;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class MenuRepoImpl implements MenuRepo {
    @Override
    public Observable<String> getMenuItems() {

        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {

                Uri categoryContentProvider = Uri.parse("content://com.suitepad.datasource/menu");

                Cursor menuItemsCursor = App.get().getContentResolver().query(categoryContentProvider, null,
                        null, null, null);

                List<MenuItem> menuItems = getItemsFromCursor(menuItemsCursor);

                Gson gson = new Gson();
                String response = gson.toJson(menuItems);


                return  response;
            }
        });
    }


    /**
     * Get Menu items from Cursor
     *
     * @param cursor
     * @return
     */
    private List<MenuItem> getItemsFromCursor(Cursor cursor) {

        List<MenuItem> menuItems = new ArrayList<>();
        while (cursor.moveToNext()) {
            String UUID = cursor.getString(MenuContract.MenuEntryIndex.COLUMN_UUID);
            String name = cursor.getString(MenuContract.MenuEntryIndex.COLUMN_NAME);
            int price = cursor.getInt(MenuContract.MenuEntryIndex.COLUMN_PRICE);
            String type = cursor.getString(MenuContract.MenuEntryIndex.COLUMN_TYPE);
            menuItems.add(new MenuItem(UUID, name, price, type));
        }
        return menuItems;
    }
}
