package com.developer.fabian.phonebookandroid.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "contact")
data class Contact(@ColumnInfo(name = "name") @NotNull var name: String) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
