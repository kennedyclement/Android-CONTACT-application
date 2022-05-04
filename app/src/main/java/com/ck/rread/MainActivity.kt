package com.ck.rread

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var layoutManagers: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    lateinit var myContactList: ArrayList<ContactsDetails>

    lateinit var floatingButton: FloatingActionButton

    val contactRequestCode = 101
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        searchView   = findViewById(R.id.search_view)

            floatingButton = findViewById(R.id.floatingActionButton_btn)

        floatingButton.setOnClickListener() {

            intent = Intent(this, CreateNewContact::class.java)
            startActivity(intent)


        }
        layoutManagers = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManagers
        checkForPermission(android.Manifest.permission.READ_CONTACTS, "Contact", 101)

        searchContact()

    }

    private fun searchContact() {

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let { filterContact(it) }

            return true
            }


        })


    }

    private fun filterContact(newText: String) {
var filteredList = ArrayList<ContactsDetails>()
        for (items in myContactList){
            if (items.name.toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(items)
            }
        }

//        adapter = RecyclerAdapter(filteredList,this)
     adapter = RecyclerAdapter(filteredList,this)
recyclerView.adapter = adapter

    }


    @SuppressLint("Range")
    fun getContacts(): ArrayList<ContactsDetails> {

        var contactList = ArrayList<ContactsDetails>()

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
        )
        cursor?.let {
            while (cursor.moveToNext()) {
                var contactsDetails = ContactsDetails()

                contactsDetails.name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                contactsDetails.number =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))


                val photo_uri =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

                if (photo_uri != null)
                    contactsDetails.images = MediaStore.Images.Media.getBitmap(
                        contentResolver,
                        Uri.parse(photo_uri)
                    )

                contactList.add(contactsDetails)

            }


        }
        cursor?.close()
        insertContacts(contactList)
        adapter = RecyclerAdapter(contactList,this)

        recyclerView.adapter = adapter


        return myContactList
    }

    fun insertContacts(contactList: ArrayList<ContactsDetails>) {


        val contactTable = ContactTable(this)

        myContactList = contactTable.insertData(contactList)
        adapter = RecyclerAdapter(myContactList, this)
        recyclerView.adapter = adapter


        Toast.makeText(this, "successfully inserted", Toast.LENGTH_SHORT).show()

    }

    private fun checkForPermission(permission: String, name: String, requestCode: Int) {
        when {

            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
//                getContacts()
                Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_SHORT)
                    .show()
                getContacts()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                showDialog(permission, name, requestCode)
            }
            else -> {
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("Give permission to access your $name.It is required to use this app")
            setTitle("Permission Required")
            setPositiveButton("OK") { dialog, which ->
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(permission),
                    requestCode
                )
            }
            setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(applicationContext, "Permission is not granted", Toast.LENGTH_SHORT)
                    .show()

            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "$name permission refused", Toast.LENGTH_SHORT)
                    .show()

            } else {
                getContacts()
                Toast.makeText(
                    applicationContext,
                    "$name permission granted ORP",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
        }

        when (requestCode) {
            contactRequestCode -> innerCheck("Contact")

        }
    }
}




