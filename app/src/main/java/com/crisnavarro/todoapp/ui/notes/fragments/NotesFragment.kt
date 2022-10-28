package com.crisnavarro.todoapp.ui.notes.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.databinding.FragmentNotesBinding

class NotesFragment : Fragment(R.layout.fragment_notes), MenuProvider {

    private var binding: FragmentNotesBinding? = null

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
    }


    private fun setUpViews() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setUpListener() {
        with(binding!!) {

            fabAddNote.setOnClickListener { goToUpdateNote() }
            recyclerViewNotes.setOnClickListener { goToUpdateNote() }

        }
    }

    private fun goToAddNote() {
        val navigate = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
        findNavController().navigate(navigate)
    }

    private fun goToUpdateNote() {
        val navigate = NotesFragmentDirections.actionNotesFragmentToUpdateNoteFragment()
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