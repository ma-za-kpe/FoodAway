package com.kpe.foodaway.framework.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cv_table")
class ClientVendor(
    @ColumnInfo(name = "clientorvendor")
    var clientorvendor: String,
    @ColumnInfo(name = "both")
    var both: String,
){
    fun ClientVendor() {}
    @field: PrimaryKey(autoGenerate = true)
    var id: Int = 0
}