package com.developer.fabian.phonebookandroid.dao

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.developer.fabian.phonebookandroid.entity.Contact

@Database(entities = [(Contact::class)], version = 1)
abstract class ContactRoomDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: ContactRoomDatabase? = null

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        private class PopulateDbAsync(contactRoomDatabase: ContactRoomDatabase) : AsyncTask<Void, Void, Void>() {

            private var mDao: ContactDao = contactRoomDatabase.contactDao()

            override fun doInBackground(vararg params: Void): Void? {
                mDao.deleteAll()

                val contact = Contact("Police")
                mDao.insert(contact)

                return null
            }
        }

        fun getDatabase(context: Context): ContactRoomDatabase {
            if (INSTANCE == null) {
                synchronized(ContactRoomDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                ContactRoomDatabase::class.java, "contact_database")
                                .addCallback(sRoomDatabaseCallback)
                                .build()
                    }
                }
            }

            return INSTANCE!!
        }
    }

    abstract fun contactDao(): ContactDao

}
