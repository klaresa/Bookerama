package com.example.bookerama

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bookerama.models.Book

class BookAdapter(var items: List<Book>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // classe pai
    // var items = items

    override fun getItemCount() = items.size
    // Essa linha de cima equivale a esta de baixo:
    //    override fun getItemCount(): Int {
    //        return items.size
    //    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = items[position]
        if (holder is BookViewHolder){
            holder.titleTextView.text = book.title
            holder.authorTextView.text = book.author
        }

    }

    // views tem contexto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context).inflate(R.layout.book_card, parent, false)

        val viewHolder = BookViewHolder(card)
        return viewHolder

    }

    fun updateData(booksList: List<Book>){
        items = booksList
        notifyDataSetChanged()
    }


    // aqui eu quero segurar as views da minha item view
    // o card
    // passei no super (itemView)
    // passar o construtor pra passar no super
    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_textview)
        val authorTextView = itemView.findViewById<TextView>(R.id.author_textview)
    }

}