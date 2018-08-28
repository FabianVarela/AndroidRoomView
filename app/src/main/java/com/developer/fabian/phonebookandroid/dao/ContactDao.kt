package com.developer.fabian.phonebookandroid.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.developer.fabian.phonebookandroid.entity.Contact

@Dao
interface ContactDao {

    @Insert
    fun insert(contact: Contact)

    @Query("SELECT * from contact ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * from contact WHERE id = :id")
    fun getContact(id: Long): LiveData<Contact>

    @Update
    fun update(contact: Contact)

    @Query("DELETE FROM contact WHERE id = :id")
    fun deleteContact(id: Long)

    @Query("DELETE FROM contact")
    fun deleteAll()

}
