package com.example.stevdza_san_notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.databinding.FragmentAddNoteBinding
import com.example.stevdza_san_notes.room.entities.NoteTable
import com.example.stevdza_san_notes.ui.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private val viewModel: NotesViewModel by viewModels()
    private val binding: FragmentAddNoteBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            AddDatatoDatabase()
            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        }

        val priorities = resources.getStringArray(R.array.priority)
        val arrayAdapters = ArrayAdapter(requireContext(), R.layout.dropdown_item, priorities)
        binding.actv.setAdapter(arrayAdapters)

    }

    private fun AddDatatoDatabase() {

        val mtitle = binding.titleEt.text.toString()
        val mbody = binding.descriptionEt.text.toString()
        val mPriority = binding.actv.text.toString()

        // create data obj
        val noteTable = NoteTable(0, mtitle, mbody, viewModel.parsePriority(mPriority))
        viewModel.saveNote(noteTable)

    }

    override fun onResume() {
        super.onResume()
        val priorities = resources.getStringArray(R.array.priority)
        val arrayAdapters = ArrayAdapter(requireContext(), R.layout.dropdown_item, priorities)
        binding.actv.setAdapter(arrayAdapters)
    }

}