package com.crisnavarro.todoapp.ui.updatenotes.fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.data.db.entities.NoteEntity
import com.crisnavarro.todoapp.data.models.Priority
import com.crisnavarro.todoapp.databinding.FragmentUpdateNoteBinding
import com.crisnavarro.todoapp.ui.updatenotes.viewmodel.UpdateNoteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteFragment : Fragment(R.layout.fragment_update_note), MenuProvider {

    private var binding: FragmentUpdateNoteBinding? = null
    private val viewModel: UpdateNoteViewModel by viewModels()
    private val args by navArgs<UpdateNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() = with(binding!!) {

        requireActivity().addMenuProvider(
            this@UpdateNoteFragment,
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

        setUpAdapter()
        editTextTitleNote.setText(args.note.title)
        editTextNoteDesc.setText(args.note.description)

    }

    private fun setUpAdapter() = with(binding!!) {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.priority_array)
        ).apply { autoCompleteTextView.setAdapter(this) }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.update_note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.icon_save -> {
                updateNote()
                true
            }
            R.id.icon_delete -> {
                deleteNote()
                findNavController().popBackStack()
                true
            }
            else -> false
        }
    }

    private fun deleteNote() = viewModel.deleteNote(args.note)

    private fun updateNote() = with(binding!!) {

        if (isReadyToUpdate()) {

            args.note.title = editTextTitleNote.text.toString()
            args.note.priority = getPriority()
            args.note.description = editTextNoteDesc.text.toString()

            viewModel.updateNote(args.note)
            findNavController().popBackStack()
        } else
            Snackbar.make(requireView(), "Fill the fields before continue", Snackbar.LENGTH_SHORT)
                .show()

    }

    private fun isReadyToUpdate(): Boolean {
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