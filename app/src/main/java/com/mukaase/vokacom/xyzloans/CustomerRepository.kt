package com.mukaase.vokacom.xyzloans

import androidx.lifecycle.LiveData

class CustomerRepository(private val dao: CustomerDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Customer>> = dao.getAllCustomers()

    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    // This ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    suspend fun insert(customer: Customer) {
        dao.insert(customer)
    }
}
