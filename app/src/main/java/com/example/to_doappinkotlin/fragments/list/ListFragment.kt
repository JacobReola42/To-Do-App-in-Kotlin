package com.example.to_doappinkotlin.fragments.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.to_doappinkotlin.R
import com.example.to_doappinkotlin.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        binding.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        // Set Menu
        setHasOptionsMenu(true)

        return binding.root


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)

    }
}