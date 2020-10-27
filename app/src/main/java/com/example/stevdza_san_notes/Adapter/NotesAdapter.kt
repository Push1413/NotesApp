package com.example.stevdza_san_notes.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.room.NoteTable
import kotlinx.android.synthetic.main.item_list.view.*

class NotesAdapter:RecyclerView.Adapter<NotesAdapter.NotesViewholder>() {

    inner class NotesViewholder(itemView:View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<NoteTable>() {
        override fun areItemsTheSame(oldItem: NoteTable, newItem: NoteTable): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteTable, newItem: NoteTable): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewholder {
        return NotesViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewholder, position: Int) {
        val note = differ.currentList[position]
        holder.itemView.apply {
            title_txt.text = note.Title
            description_txt.text = note.Body
            setOnClickListener {
                onItemClickListener?.let { it(note) }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private var onItemClickListener: ((NoteTable) -> Unit)? = null

    fun setOnItemClickListener(listener: (NoteTable) -> Unit) {
        onItemClickListener = listener
    }

}