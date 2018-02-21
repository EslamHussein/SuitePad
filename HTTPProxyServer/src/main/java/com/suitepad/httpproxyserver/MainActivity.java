package com.suitepad.httpproxyserver;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Uri categoryContentProvider = Uri.parse("content://com.suitepad.datasource/menu");

        Cursor categoriesCursor = getContentResolver().query(categoryContentProvider, null,
                null, null, null);

        while (categoriesCursor.moveToNext()) {

            Log.d("Tags", "onCreate() returned: " + Arrays.toString(categoriesCursor.getColumnNames()));

        }
    }
}
