package com.ck.rread

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class ContactTable(context: Context) {

    val dbHelper = DBHelper(context)


    companion object {

        val tableName = "MyContacts"
        val contactId = "contactId"
        val name = "name"
        val number = "number"

        fun createTable(db: SQLiteDatabase) {

            db?.execSQL("create table $tableName ($contactId Integer primary key autoincrement ,$name Text,$number Text) ")


        }
    }

    fun insertData(contactList: ArrayList<ContactsDetails>): ArrayList<ContactsDetails> {

        var result:Long = 0
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val contentValue = ContentValues()

        for (items in contactList) {
            contentValue.put(name, items.name)
            contentValue.put(number, items.number)
            contentValue.put(number, items.images.toString())


             result = db.insert(tableName, null, contentValue)// error
        }

        db.close()
        return contactList
    }

    fun insertContact(contactsDetails: ContactsDetails):Boolean{
         var result:Long = 0
        val db: SQLiteDatabase = dbHelper.writableDatabase

        try {


            val cv = ContentValues()



            cv.put(name, contactsDetails.name)
            cv.put(number, contactsDetails.number)

           result = db.insert(tableName, null, cv)
        } catch (exception: Exception) {


        } finally {
            db.close()
        }

        return result != -1L

    }

    fun getEveryOneDetails(): ArrayList<ContactsDetails> {

/*
        val customerList:List<CustomerModel> = ArrayList()
*/
        val customerList = ArrayList<ContactsDetails>()

        val db = dbHelper.readableDatabase

        val query = "SELECT * FROM $tableName"

        val cursor = db.rawQuery(query, null, null)


        db.close()
        cursor.close()




        return customerList


    }
}