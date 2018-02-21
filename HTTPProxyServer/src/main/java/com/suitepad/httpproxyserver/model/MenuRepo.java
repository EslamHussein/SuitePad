package com.suitepad.httpproxyserver.model;

import android.database.Cursor;

import com.suitepad.commanconstant.MenuItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by EslamHussein on 2/21/18.
 */

public interface MenuRepo  {

    Observable<String> getMenuItems();

    Observable<String> getTest();


}
