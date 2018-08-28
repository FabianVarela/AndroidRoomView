package com.developer.fabian.phonebookandroid.ui.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.developer.fabian.phonebookandroid.entity.Contact
import com.developer.fabian.phonebookandroid.repository.ContactRepository

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private var contactRepository: ContactRepository = ContactRepository(application)
    private var mContacts: LiveData<List<Contact>>

    init {
        mContacts = contactRepository.getAllContacts()
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return mContacts
    }

    fun getContact(id: Long): LiveData<Contact> {
        return contactRepository.getContact(id)
    }

    fun insert(contact: Contact) {
        contactRepository.insert(contact)
    }

    fun update(contact: Contact) {
        contactRepository.update(contact)
    }

    fun deleteContact(contact: Contact) {
        contactRepository.delete(contact)
    }

    fun delete() {
        contactRepository.delete(null)
    }

}
