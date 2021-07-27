package com.example.to_doappinkotlin.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_doappinkotlin.R
import com.example.to_doappinkotlin.data.models.ToDoData
import com.example.to_doappinkotlin.data.viewmodel.ToDoViewModel
import com.example.to_doappinkotlin.databinding.FragmentAddBinding
import com.example.to_doappinkotlin.databinding.FragmentListBinding
import com.example.to_doappinkotlin.fragments.SharedViewModel

/* See SharedViewModel.kt */

class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        // Set Menu
        setHasOptionsMenu(true)

        // Spinner Item Selected Listener, linked from SharedViewModel.kt
        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    /* Needed to manually check data on SQLite database. for DeviceFile explorer to
       data > data > com.example.to_doappinkotlin > databases > *. Open and check with
       SQLite and go to browse data > to do table when using todo_database file.
       For reading data. NEED to use SQLite Browser.
     */
    // Needed to check data on SQLite database. see SharedViewModel.kt
    private fun insertDataToDb() {
        val mTitle = binding.titleEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mDescription = binding.descriptionEt.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if(validation){
            // Insert Data to Database
            val newData = ToDoData(
                0,     // ROOM database automatically increases this column.
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}