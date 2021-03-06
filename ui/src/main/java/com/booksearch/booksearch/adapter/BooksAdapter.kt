package com.booksearch.booksearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.booksearch.booksearch.R
import com.booksearch.booksearch.databinding.ItemBookBinding
import com.booksearch.booksearch.extension.loadImageByLink
import com.booksearch.booksearch.viewholder.BookViewHolder
import com.booksearch.domain.model.Book


class BooksAdapter : ListAdapter<Book, BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(bookViewHolder: BookViewHolder, position: Int) {
        getItem(position)?.let { book ->
            with(bookViewHolder.binding) {
                textViewTitle.text = book.title
                textViewAuthors.text = book.authors ?: ""
                book.imageLink?.let { imageLink ->
                    imageViewCover.loadImageByLink(imageLink)
                } ?: imageViewCover.setImageResource(R.drawable.ic_image)
            }
        }
    }

}

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.authors == newItem.authors &&
                oldItem.imageLink == newItem.imageLink
    }

}