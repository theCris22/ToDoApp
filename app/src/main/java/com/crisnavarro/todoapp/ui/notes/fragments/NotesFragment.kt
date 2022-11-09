package com.crisnavarro.todoapp.ui.notes.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.data.db.entities.NoteEntity
import com.crisnavarro.todoapp.databinding.FragmentNotesBinding
import com.crisnavarro.todoapp.ui.notes.adapters.NotesAdapter
import com.crisnavarro.todoapp.ui.notes.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes), MenuProvider {

    private var binding: FragmentNotesBinding? = null
    private lateinit var adapter: NotesAdapter
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpListener()
        setUpObservers()
    }


    private fun setUpViews() = with(binding!!) {
        requireActivity().addMenuProvider(
            this@NotesFragment,
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

        adapter = NotesAdapter(requireContext()) { goToUpdateNote(it) }
        recyclerViewNotes.adapter = adapter
        recyclerViewNotes.setHasFixedSize(true)
    }

    private fun setUpListener() {
        with(binding!!) {
            fabAddNote.setOnClickListener { goToAddNote() }
        }
    }

    private fun setUpObservers() {
        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun goToAddNote() {
        val navigate = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
        findNavController().navigate(navigate)
    }

    private fun goToUpdateNote(note: NoteEntity) {
        val navigate = NotesFragmentDirections.actionNotesFragmentToUpdateNoteFragment(note)
        findNavController().navigate(navigate)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_notes_fragment, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        /*return when (menuItem.itemId) {
            R.id.clear_text_menu -> {
                binding!!.editTextHash.text.clear()
                true
            }
            else -> false
        }*/
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}