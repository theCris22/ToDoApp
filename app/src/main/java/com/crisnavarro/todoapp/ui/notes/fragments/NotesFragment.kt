package com.crisnavarro.todoapp.ui.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crisnavarro.todoapp.R
import com.crisnavarro.todoapp.databinding.FragmentNotesBinding

class NotesFragment : Fragment(R.layout.fragment_notes) {

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
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}