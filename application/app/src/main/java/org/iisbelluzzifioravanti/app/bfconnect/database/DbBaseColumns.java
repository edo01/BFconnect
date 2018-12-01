package org.iisbelluzzifioravanti.app.bfconnect.database;

import android.provider.BaseColumns;

public class DbBaseColumns implements BaseColumns {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbBaseColumns() {}

    public static final String TABLE_NAME = "rooms";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_IMAGE = "image";

}
