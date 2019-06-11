package com.example.bookerama

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.bookerama.database.allBooks
import com.example.bookerama.models.Book

class BooksViewModel: ViewModel() {

    val liveBooks = MutableLiveData<List<Book>>().apply { value =  allBooks }

    fun addNewBook(book: Book){
        liveBooks.value = liveBooks.value?.plus(book)
    }
}