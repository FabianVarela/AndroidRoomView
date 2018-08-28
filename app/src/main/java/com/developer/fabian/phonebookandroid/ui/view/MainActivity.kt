package com.developer.fabian.phonebookandroid.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.developer.fabian.phonebookandroid.R
import com.developer.fabian.phonebookandroid.entity.Contact
import com.developer.fabian.phonebookandroid.ui.adapter.ContactListAdapter
import com.developer.fabian.phonebookandroid.ui.viewModel.ContactViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NEW_CONTACT_ACTIVITY_REQUEST_CODE: Int = 1
    }

    private lateinit var mContactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = ContactListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewContactActivity::class.java)
            startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE)
        }

        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        mContactViewModel.getAllContacts().observe(this, Observer {
            adapter.setContacts(it!!)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = Contact(data!!.getStringExtra(NewContactActivity.EXTRA_REPLY))
            mContactViewModel.insert(word)
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

}
