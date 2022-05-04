package com.ck.rread

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var list: List<ContactsDetails>, val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val TAG = "recyclerView adapter"

//    var list: List<ContactsDetails> = item

//    var context:Context = context1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        Log.d(TAG,"onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        Log.d(TAG,"onBindViewHolder")

        holder.name.text = list[position].name

//        holder.number.text = list[position].number

//        holder.itemImage.setImageResource(images[position])

        if (list[position].images != null)
            holder.itemImage.setImageBitmap(list[position].images)
        else
            holder.itemImage.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_launcher_round))


    }

    override fun getItemCount(): Int {
//        Log.d(TAG,"getItemCount")

        return list.size

    }

    fun filteredList(filteredList: ArrayList<ContactsDetails>) {

        list = filteredList
        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var name: TextView
//        var number: TextView

        init {
//            Log.d(TAG,"onCreateViewHolder")

            itemImage = itemView.findViewById(R.id.image_view)
            name = itemView.findViewById(R.id.name)
//            number = itemView.findViewById(R.id.number)

            itemImage.setOnClickListener() {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "This is  ${list[position].name}", Toast.LENGTH_LONG)
                    .show()
            }
        }


    }
}

