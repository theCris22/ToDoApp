package com.crisnavarro.todoapp.ui.updatenotes.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.databinding.FragmentUpdateNoteBinding

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note), MenuProvider {

    private var binding: FragmentUpdateNoteBinding? = null

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

    private fun setUpViews() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
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