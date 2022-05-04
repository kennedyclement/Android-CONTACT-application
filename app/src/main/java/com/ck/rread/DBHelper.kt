package com.ck.rread

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import java.util.concurrent.ForkJoinPool

class DBHelper(context: Context) : SQLiteOpenHelper(context, "MyContacts", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {

            ContactTable.createTable(it)
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {


    }


}
