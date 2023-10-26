package com.example.countries.db

import android.annotation.SuppressLint

import android.content.ContentValues

import android.content.Context

import android.database.sqlite.SQLiteDatabase


class MyDbManager(private val context: Context) {
    private val myDbHelper: MyDbHelper = MyDbHelper(context)
    private lateinit var db: SQLiteDatabase

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(name: String, population: String, description:String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_POPULATION, population)
            put(MyDbNameClass.COLUMN_NAME_DESCRIPTION, description)
        }
        db.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDbData_Names(): ArrayList<String> {
        val dataList_name = ArrayList<String>()
        val cursor = db.query(
            MyDbNameClass.TABLE_NAME,
            null, null, null, null, null, null
        )

        while (cursor?.moveToNext()!!) {
            val dataText_name = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_NAME))
            dataList_name.add(dataText_name)
        }
        cursor.close()
        return dataList_name
    }

    @SuppressLint("Range")
    fun readDbData_Description(): ArrayList<String> {
        val dataList_description = ArrayList<String>()
        val cursor = db.query(
            MyDbNameClass.TABLE_NAME,
            null, null, null, null, null, null
        )

        while (cursor?.moveToNext()!!) {
            val dataText_description = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_DESCRIPTION))
            dataList_description.add(dataText_description)
        }
        cursor.close()
        return dataList_description
    }

    @SuppressLint("Range")
    fun readDbData_Population(): ArrayList<String> {
        val dataList_population = ArrayList<String>()
        val cursor = db.query(
            MyDbNameClass.TABLE_NAME,
            null, null, null, null, null, null
        )

        while (cursor?.moveToNext()!!) {
            val dataText_population = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_POPULATION))
            dataList_population.add(dataText_population)
        }
        cursor.close()
        return dataList_population
    }

    fun deleteFromDb(name: String) {
        val selection = "${MyDbNameClass.COLUMN_NAME_NAME} LIKE ?"
        val selectionArgs = arrayOf(name)
        db.delete(MyDbNameClass.TABLE_NAME, selection, selectionArgs)
    }
    fun updateInDb(name: String, newName: String, population: String, description: String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, newName)
            put(MyDbNameClass.COLUMN_NAME_POPULATION, population)
            put(MyDbNameClass.COLUMN_NAME_DESCRIPTION, description)
        }
        val selection = "${MyDbNameClass.COLUMN_NAME_NAME} LIKE ?"
        val selectionArgs = arrayOf(name)
        db.update(MyDbNameClass.TABLE_NAME, values, selection, selectionArgs)
    }

    fun closeDb(){
        myDbHelper.close()
    }
}