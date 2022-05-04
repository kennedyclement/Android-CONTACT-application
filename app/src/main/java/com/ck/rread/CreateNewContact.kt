package com.ck.rread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateNewContact : AppCompatActivity() {

    lateinit var nameEt: EditText
    lateinit var numberEt: EditText
    lateinit var saveBtn: Button
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        saveBtn = findViewById(R.id.save_btn)
        nameEt = findViewById(R.id.name_et)
        numberEt = findViewById(R.id.number_et)


        supportActionBar?.title = "Creating new Contact"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        saveContact()


    }


    fun saveContact() {

        val contactsDetails = ContactsDetails()


        contactsDetails.name = nameEt.text.toString()
        contactsDetails.number = numberEt.text.toString()


        saveBtn.setOnClickListener {

            if (nameEt.text.isEmpty() || numberEt.text.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                val contactTable = ContactTable(this)
                val isInserted = contactTable.insertContact(contactsDetails)

                nameEt.setText("")
                numberEt.setText("")

                Toast.makeText(this, "isInserted =  $isInserted", Toast.LENGTH_SHORT).show()


            }
        }

    }
}








