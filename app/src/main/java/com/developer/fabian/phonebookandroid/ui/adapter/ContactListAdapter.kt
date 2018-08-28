package com.developer.fabian.phonebookandroid.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.developer.fabian.phonebookandroid.R
import com.developer.fabian.phonebookandroid.entity.Contact

class ContactListAdapter(context: Context) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mContact: List<Contact>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        if (mContact != null) {
            val current = mContact!![position]
            holder.textView.text = current.name
        } else {
            holder.textView.text = "No Word"
        }
    }

    override fun getItemCount(): Int {
        return if (mContact != null)
            mContact!!.size
        else 0
    }

    fun setContacts(contacts: List<Contact>) {
        mContact = contacts
        notifyDataSetChanged()
    }

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.textView)
    }

}
