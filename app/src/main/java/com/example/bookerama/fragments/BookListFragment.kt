package com.example.bookerama.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookerama.BookAdapter
import com.example.bookerama.BooksViewModel

import com.example.bookerama.R
import kotlinx.android.synthetic.main.fragment_book_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [BookListFragment.newInstance] factory method to create an instance of this fragment.
 */
class BookListFragment : Fragment() {

    // cria o booksViewModel do tipo BooksViewModel com a promessa do lateinit
    private lateinit var booksViewModel: BooksViewModel

    // primeiro cria a view
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.let {
            booksViewModel = ViewModelProviders
                .of(it)
                .get(BooksViewModel::class.java)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    // uma vez criada a view
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        // configurando a RecyclerView
        bookList_recyclerView.adapter = BookAdapter(booksViewModel.liveBooks.value ?: listOf())

        // da forma a RecyclerView
        activity?.let {
            bookList_recyclerView.layoutManager = LinearLayoutManager(it)
            bookList_recyclerView.addItemDecoration(DividerItemDecoration(it, DividerItemDecoration.VERTICAL))
        }
    }

    private fun subscribe() {
        booksViewModel.liveBooks.observe(this, Observer {
            val bookAdapter = bookList_recyclerView.adapter as? BookAdapter
            bookAdapter?.updateData(it ?: listOf())
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = BookListFragment()
    }
}