package com.hejunlin.liveplayback.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HM on 2017/3/14 20:59
 */

public class WeatherDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "WeatherDBHelper";
    private static final int VERSION = 1;

    /**
     * <p>
     * Title: <／p>
     * <p>
     * Description: <／p>
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     * @param errorHandler
     */
    public WeatherDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    /*
     * （非 Javadoc） <p>Title: onCreate</p> <p>Description: </p>
     *
     * @param paramSQLiteDatabase
     *
     * @see
     * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
     * .SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
        // TODO Auto-generated method stub

        String sql = "create table weather_table(id integer PRIMARY KEY AUTOINCREMENT," + "date varchar(20),"
                + "dayPictureUrl varchar(50)," + "nightPictureUrl varchar(50)," + "weather varchar(30),"
                + "temperature varchar(20))";
        // 输出创建数据库的日志信息
        Log.i(TAG, "create Database------------->");
        // execSQL函数用于执行SQL语句
        paramSQLiteDatabase.execSQL(sql);
    }

    /*
     * （非 Javadoc） <p>Title: onUpgrade</p> <p>Description: </p>
     *
     * @param paramSQLiteDatabase
     *
     * @param paramInt1
     *
     * @param paramInt2
     *
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.
     * sqlite.SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        Log.i(TAG, "update Database------------->");
    }

}

