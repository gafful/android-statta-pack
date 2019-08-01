package com.mukaase.vokacom.xyzloans

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CustomerDao {
    @Query("SELECT * from Customer ORDER BY lastName ASC")
    fun getAllCustomers(): LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(customer: Customer)

    @Query("DELETE FROM Customer")
    suspend fun deleteAll()
}