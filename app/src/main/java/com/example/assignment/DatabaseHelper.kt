package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserCredentials.db"
        private const val TABLE_NAME = "credentials"
        private const val COL_USERNAME = "username"
        private const val COL_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_USERNAME TEXT PRIMARY KEY, $COL_PASSWORD TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertCredentials(username: String, password: String) {
        val db = this.writableDatabase
        val query = "INSERT INTO $TABLE_NAME ($COL_USERNAME, $COL_PASSWORD) VALUES ('$username', '$password')"
        db.execSQL(query)
        db.close()
    }

    @SuppressLint("Range")
    fun getCredentials(): Pair<String, String>? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        var credentials: Pair<String, String>? = null

        if (cursor.moveToFirst()) {
            val username = cursor.getString(cursor.getColumnIndex(COL_USERNAME))
            val password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))
            credentials = Pair(username, password)
        }

        cursor.close()
        db.close()
        return credentials
    }
}