package org.iisbelluzzifioravanti.app.bfconnect.database;

import android.provider.BaseColumns;

public class DbBaseColumns implements BaseColumns {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbBaseColumns() {}

    public static final String TABLE_NAME = "rooms";
    public static final String KEY_ROOMID = "room_id";
    public static final String KEY_TYPE = "type";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_IMAGE2 = "image2";
    public static final String KEY_IMAGE3 = "image3";
    public static final String KEY_IMAGE4 = "image4";
    public static final String KEY_IMAGE5 = "image5";
    public static final String KEY_IMAGE6 = "image6";

}
