package com.example.stevdza_san_notes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.stevdza_san_notes.Adapter.NotesAdapter
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.databinding.FragmentHomeBinding
import com.example.stevdza_san_notes.ui.MainActivity
import com.example.stevdza_san_notes.ui.NotesViewModel
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var notesAdapter: NotesAdapter
    lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        notesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("note",it)
            }
            findNavController().navigate(R.id.action_homeFragment_to_updateFragment,bundle)
        }


        binding.fab.setOnClickListener {
findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = notesAdapter.differ.currentList[position]
                viewModel.deleteNote(note)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveNote(note)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rv)
        }

        viewModel.getAllSavedNews().observe(viewLifecycleOwner, Observer { notes ->
            notesAdapter.differ.submitList(notes)
        },)
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter()
        binding.rv.apply {
            adapter = notesAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}