package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量

    private TodoContract() {
    }

    public static class Todolist implements BaseColumns{
        public static final String TABLE_TITLE="entry";
        public static final String COLUMN_NAME_ONE = "content";
        public static final String COLUMN_NAME_TWO = "state";
        public static final String COLUMN_NAME_THREE = "TIME";
        public static final String COLUMN_NAME_FOUR = "Priority";

    }

    public static final String SQL_CREATE_ENTRIES ="CREATE TABLE "+Todolist.TABLE_TITLE+"("+Todolist._ID+" INTEGER PRIMARY KEY,"+Todolist.COLUMN_NAME_ONE+" TEXT,"+Todolist.COLUMN_NAME_TWO+" INTEGER, "+Todolist.COLUMN_NAME_THREE+" INTEGER,"+Todolist.COLUMN_NAME_FOUR+" INTEGER)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + Todolist.TABLE_TITLE;

}
