package com.example.stevdza_san_notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.room.NoteTable
import com.example.stevdza_san_notes.ui.MainActivity
import com.example.stevdza_san_notes.ui.NotesViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddNoteFragment : Fragment(R.layout.fragment_add_note) {
    lateinit var viewModel: NotesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        btn.setOnClickListener{
            AddDatatoDatabase()
            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        }
    }

    private fun AddDatatoDatabase(){
        val mtitle = title_et.text.toString()
        val mbody = description_et.text.toString()

        // create data obj
        val NoteTable = NoteTable(0,mtitle,mbody,1)
        viewModel.saveNote(NoteTable)


    }

}