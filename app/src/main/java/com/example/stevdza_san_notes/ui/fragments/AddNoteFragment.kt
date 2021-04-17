package com.example.stevdza_san_notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.databinding.FragmentAddNoteBinding
import com.example.stevdza_san_notes.room.NoteTable
import com.example.stevdza_san_notes.ui.MainActivity
import com.example.stevdza_san_notes.ui.NotesViewModel

class AddNoteFragment : Fragment() {
    lateinit var viewModel: NotesViewModel
    private var _binding:FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        binding.btn.setOnClickListener{
            AddDatatoDatabase()
            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        }
    }

    private fun AddDatatoDatabase(){

        val mtitle = binding.titleEt.text.toString()
        val mbody = binding.descriptionEt.text.toString()

        // create data obj
        val noteTable = NoteTable(0,mtitle,mbody,1)
        viewModel.saveNote(noteTable)


    }

}