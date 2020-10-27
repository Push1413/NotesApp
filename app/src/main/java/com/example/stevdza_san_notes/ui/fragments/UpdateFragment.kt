package com.example.stevdza_san_notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.room.NoteTable
import com.example.stevdza_san_notes.ui.MainActivity
import com.example.stevdza_san_notes.ui.NotesViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_add_note.btn
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment(R.layout.fragment_update) {
    lateinit var viewModel: NotesViewModel
    val args:UpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val note = args.note

        current_title_et.setText(note.Title)
        current_description_et.setText(note.Body)

        btn.setOnClickListener{
            updateDatatoDatabase()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
    }

    private fun updateDatatoDatabase(){
        val mtitle = current_title_et.text.toString()
        val mbody = current_description_et.text.toString()
        val id = args.note.id
        // create data obj
        val NoteTable = NoteTable(id,mtitle,mbody,1)
        viewModel.updateNote(NoteTable)
    }
}