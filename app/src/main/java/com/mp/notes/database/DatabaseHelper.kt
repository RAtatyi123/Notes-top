package com.mp.notes.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context):
        SQLiteOpenHelper(context, dbName, null, 1) {


    val colID = "ID"
    val colName = "Name"
    val colDesc = "Description"
    val dbVersion = 1
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS $dbTable ($colID INTEGER PRIMARY KEY," +
            "$colName TEXT, $colDesc TEXT);"

    val context = context

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(sqlCreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $dbName")
        onCreate(db)
    }

    fun insert(name: String, desc: String):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, name)
        contentValues.put(COL_2, desc)

       return db.insert(dbTable, null, contentValues)
    }

    fun getNotes(): Cursor {
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT * FROM $dbTable", null)
        return res
    }

    fun delete(name: String, desc: String){
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT $COL_0 FROM $dbTable WHERE $COL_1 = '$name'", null)
        var id = 0
        while(res.moveToNext()){

            id = res.getString(0).toInt()
        }
        db.delete(dbTable, "ID = $id", null)

    }

    fun getID(name: String, desc: String): String{
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT $COL_0 FROM $dbTable WHERE $COL_1 = '$name' AND $COL_2 = '$desc'", null)
        var id = ""
        while(res.moveToNext()){
            id = res.getString(0)
        }
        return id
    }

    fun update(id: String, name: String, desc: String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_0, id)
        contentValues.put(COL_1, name)
        contentValues.put(COL_2, desc)

        db.update(dbTable,contentValues, "ID = ?", arrayOf(id))

    }


    companion object {
        val dbName = "Notes"
        val dbTable = "NotesTable"
        val COL_0 = "ID"
        val COL_1 = "Name"
        val COL_2 = "Description"
    }

}