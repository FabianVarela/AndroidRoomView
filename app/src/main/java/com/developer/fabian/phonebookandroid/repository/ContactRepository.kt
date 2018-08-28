package com.developer.fabian.phonebookandroid.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.developer.fabian.phonebookandroid.dao.ContactDao
import com.developer.fabian.phonebookandroid.dao.ContactRoomDatabase
import com.developer.fabian.phonebookandroid.entity.Contact

class ContactRepository(application: Application) {

    private var mContactDao: ContactDao
    private var mContacts: LiveData<List<Contact>>

    init {
        val contactDatabase: ContactRoomDatabase = ContactRoomDatabase.getDatabase(application)

        mContactDao = contactDatabase.contactDao()
        mContacts = mContactDao.getAllContacts()
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return mContacts
    }

    fun getContact(id: Long): LiveData<Contact> {
        return mContactDao.getContact(id)
    }

    fun insert(contact: Contact) {
        InsertAsyncTask(mContactDao).execute(contact)
    }

    fun update(contact: Contact) {
        UpdateAsyncTask(mContactDao).execute(contact)
    }

    fun delete(contact: Contact?) {
        DeleteAsyncTask(mContactDao).execute(contact)
    }

    companion object {
        private class InsertAsyncTask(private var contactDao: ContactDao) : AsyncTask<Contact, Void, Void>() {

            override fun doInBackground(vararg contacts: Contact?): Void? {
                contactDao.insert(contacts[0]!!)
                return null
            }

        }

        private class UpdateAsyncTask(private var contactDao: ContactDao) : AsyncTask<Contact, Void, Void>() {

            override fun doInBackground(vararg contacts: Contact?): Void? {
                contactDao.update(contacts[0]!!)
                return null
            }

        }

        private class DeleteAsyncTask(private var contactDao: ContactDao) : AsyncTask<Contact, Void, Void>() {

            override fun doInBackground(vararg contacts: Contact?): Void? {
                if (contacts == null)
                    contactDao.deleteAll()
                else
                    contactDao.deleteContact(contacts[0]!!.id)

                return null
            }

        }
    }
}
