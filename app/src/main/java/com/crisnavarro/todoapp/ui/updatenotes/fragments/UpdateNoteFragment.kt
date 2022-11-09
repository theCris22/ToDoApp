package com.crisnavarro.todoapp.ui.updatenotes.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.databinding.FragmentUpdateNoteBinding

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note), MenuProvider {

    private var binding: FragmentUpdateNoteBinding? = null
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
        //autoCompleteTextView.setText(args.note.priority.name)
        editTextDescNote.setText(args.note.description)

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
            R.id.icon_delete -> {
                Log.e("DELETE", "DELETE")
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}