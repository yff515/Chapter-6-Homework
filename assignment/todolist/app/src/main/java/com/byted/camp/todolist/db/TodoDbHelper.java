package com.byted.camp.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class TodoDbHelper extends SQLiteOpenHelper {

    // TODO 定义数据库名、版本；创建数据库
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Todolist.db";

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//这里要改成上面的
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoContract.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        for (int i = oldVersion;i<newVersion;i++)
        {
            switch (i)
            {

                case 1:
                    try{
                        db.execSQL("ALTER TABLE "+TodoContract.Todolist.TABLE_TITLE+" ADD "+ TodoContract.Todolist.COLUMN_NAME_FOUR +" Interger ");
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;

                    default:break
                            ;
            }
        }
    }

}
