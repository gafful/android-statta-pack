package com.mukaase.vokacom.xyzloans

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CustomerRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var allCustomers: LiveData<List<Customer>>

    init {
        val wordsDao = AppDatabase.getDatabase(application, viewModelScope).customerDao()
        repository = CustomerRepository(wordsDao)
        allCustomers = repository.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Customer) = viewModelScope.launch {
        repository.insert(word)
    }

//    fun all() = viewModelScope.launch {
//        allCustomers = repository.allWords
//    }
}