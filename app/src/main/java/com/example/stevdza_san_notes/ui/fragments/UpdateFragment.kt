package com.example.stevdza_san_notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.room.entities.NoteTable
import com.example.stevdza_san_notes.ui.MainActivity
import com.example.stevdza_san_notes.ui.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_note.btn
import kotlinx.android.synthetic.main.fragment_update.*

@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.fragment_update) {
    private val viewModel: NotesViewModel by viewModels()
    val args:UpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        val mPriority = current_priorities_spinner.selectedItem.toString()
        val id = args.note.id
        // create data obj
        val NoteTable = NoteTable(id,mtitle,mbody,viewModel.parsePriority(mPriority))
        viewModel.updateNote(NoteTable)
    }
}