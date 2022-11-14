package com.crisnavarro.todoapp.ui.notes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.data.db.entities.NoteEntity
import com.crisnavarro.todoapp.data.models.Priority
import com.crisnavarro.todoapp.databinding.RowNoteBinding

class NotesAdapter(
    private val context: Context,
    private val onItemClickListener: (note: NoteEntity) -> Unit
) :
    ListAdapter<NoteEntity, NotesAdapter.NotesViewHolder>(NotesDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.setData(context, getItem(position), onItemClickListener)
    }

    inner class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = RowNoteBinding.bind(view)

        fun setData(
            context: Context,
            note: NoteEntity,
            onItemClickListener: (note: NoteEntity) -> Unit
        ) = with(binding) {
            tvTitleNote.text = note.title
            tvDescriptionNote.text = note.description

            when (note.priority) {
                Priority.LOW -> {
                    iconPriority.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            android.R.color.holo_green_light
                        )
                    )
                }
                Priority.MEDIUM -> {
                    iconPriority.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.main_yellow
                        )
                    )
                }
                Priority.HIGH -> {
                    iconPriority.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            android.R.color.holo_red_dark
                        )
                    )
                }
            }

            rowNote.setOnClickListener { onItemClickListener(note) }

        }

    }

    object NotesDiffCallBack : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity) =
            oldItem == newItem
    }

}