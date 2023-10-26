package com.example.countries.db

import android.provider.BaseColumns


object MyDbNameClass : BaseColumns {
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_NAME = "name" //title
    const val COLUMN_NAME_DESCRIPTION = "description" //subtitle
    const val COLUMN_NAME_POPULATION = "population"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyAppDb.db"

    //НАЩА БАЗА ДАННЫХ С СТОЛБАЦАМИ ID EMAIL PASSWORD
    const val CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_NAME_NAME TEXT," +
                "$COLUMN_NAME_DESCRIPTION TEXT,"+
                "$COLUMN_NAME_POPULATION TEXT)"

    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}