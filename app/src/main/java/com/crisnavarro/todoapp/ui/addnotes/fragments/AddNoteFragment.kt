package com.crisnavarro.todoapp.ui.addnotes.fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.data.db.entities.NoteEntity
import com.crisnavarro.todoapp.data.models.Priority
import com.crisnavarro.todoapp.databinding.FragmentAddNoteBinding
import com.crisnavarro.todoapp.ui.addnotes.viewmodel.AddNoteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    private var binding: FragmentAddNoteBinding? = null
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        setUpAdapter()
    }

    private fun setUpAdapter() = with(binding!!) {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.priority_array)
        ).apply { autoCompleteTextView.setAdapter(this) }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.add_note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_icon_check -> {
                insertNote()
                true
            }
            else -> false
        }
    }

    private fun insertNote() {
        with(binding!!) {

            val title = editTextTitleNote.text.toString()
            val priority = getPriority()
            val description = editTextNoteDesc.text.toString()

            if (isReadyToInsert()) {
                viewModel.insert(
                    NoteEntity(
                        title = title,
                        priority = priority,
                        description = description
                    )
                )
            } else
                Snackbar.make(requireView(), "Fill the fields before continue", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun isReadyToInsert(): Boolean {
        with(binding!!) {
            val title = editTextTitleNote.text.toString()
            val priority = autoCompleteTextView.text.toString()
            val description = editTextNoteDesc.text.toString()

            return title.isNotEmpty() && description.isNotEmpty() && priority.isNotEmpty()
        }
    }

    private fun getPriority(): Priority {
        return when (binding!!.autoCompleteTextView.text.toString()) {
            "Low" -> Priority.LOW
            "Medium" -> Priority.MEDIUM
            else -> Priority.HIGH
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}