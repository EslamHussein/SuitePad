package com.suitepad.datasource;

import java.util.ArrayList;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class MenuDataSource {

    static private ArrayList<MenuItem> menusItem = new ArrayList<>();

    static {
        menusItem.add(new MenuItem("58ab140932dfbcc4253b5236", "consectetur", 1200, "main course"));
        menusItem.add(new MenuItem("58ab140904117a99a73565e4", "adipisicing", 1400, "drink"));
        menusItem.add(new MenuItem("58ab140950d5905bd0d4752a", "commodo", 500, "main course"));
        menusItem.add(new MenuItem("58ab14097e1bf08ae9af7829", "labore", 1800, "drink"));
        menusItem.add(new MenuItem("58ab140961c812ff8022b757", "occaecat", 1400, "appetizer"));
        menusItem.add(new MenuItem("58ab1409b0148f92565506d0", "incididunt", 1300, "drink"));
        menusItem.add(new MenuItem("58ab1409a82cddf441e296c7", "ipsum", 1500, "main course"));
        menusItem.add(new MenuItem("58ab140931b3af85a6a11b10", "consectetur", 400, "drink"));
        menusItem.add(new MenuItem("58ab1409248dc6f777c816ce", "ut", 2500, "drink"));
        menusItem.add(new MenuItem("58ab14097fff45868acc9a94", "proident", 1300, "drink"));
        menusItem.add(new MenuItem("58ab14098a4ea9b9491121fa", "in", 3700, "appetizer"));

    }

    public static ArrayList<MenuItem> getMenusItem() {
        return menusItem;
    }



}
