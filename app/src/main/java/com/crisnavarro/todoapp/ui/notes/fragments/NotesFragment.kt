package com.crisnavarro.todoapp.ui.notes.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.data.db.entities.NoteEntity
import com.crisnavarro.todoapp.data.models.Priority
import com.crisnavarro.todoapp.databinding.FragmentNotesBinding
import com.crisnavarro.todoapp.ui.notes.adapters.NotesAdapter
import com.crisnavarro.todoapp.ui.notes.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes), MenuProvider,
    SearchView.OnQueryTextListener {

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
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() = with(binding!!) {
        adapter = NotesAdapter(requireContext()) { goToUpdateNote(it) }
        recyclerViewNotes.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
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
            if (it.any()) {
                adapter.submitList(it)
                binding!!.recyclerViewNotes.visibility = View.VISIBLE
                binding!!.emptyLayout.emptyLayout.visibility = View.GONE
            } else {
                binding!!.recyclerViewNotes.visibility = View.GONE
                binding!!.emptyLayout.emptyLayout.visibility = View.VISIBLE
            }
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

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.priority_low -> {
                viewModel.getLowPriorityNotes(getString(R.string.low_priority_text))
                    .observe(viewLifecycleOwner) { adapter.submitList(it) }
                true
            }
            R.id.priority_medium -> {
                viewModel.getLowPriorityNotes(getString(R.string.medium_priority_text))
                    .observe(viewLifecycleOwner) { adapter.submitList(it) }
                true
            }
            R.id.priority_high -> {
                viewModel.getLowPriorityNotes(getString(R.string.high_priority_text))
                    .observe(viewLifecycleOwner) { adapter.submitList(it) }
                true
            }
            else -> false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchNote(query ?: "")
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        searchNote(query ?: "")
        return true
    }

    private fun searchNote(query: String) {
        viewModel.searchNote("%$query%").observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}