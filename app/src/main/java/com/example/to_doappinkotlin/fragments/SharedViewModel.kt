package com.example.to_doappinkotlin.fragments

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.to_doappinkotlin.R
import com.example.to_doappinkotlin.data.models.Priority
import com.example.to_doappinkotlin.data.models.ToDoData

/* originally for AddFragment.k, this file is use to reduce redundancy for
* multiple fragments sharing the similar attributes */

class SharedViewModel(application: Application): AndroidViewModel(application) {

    /** ============================= List Fragment ============================= */

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(toDoData: List<ToDoData>){
        emptyDatabase.value = toDoData.isEmpty()
    }

    /** ============================= Add/Update Fragment ============================= */

    // Changes color of spinner for both spinners fragements
    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // positions represent priorities: High, Medium, Low
            when(position){
                // question mark is because parent is a nullable type.
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }
    }

    /* Needed to manually check data on SQLite database. for DeviceFile explorer to
       data > data > com.example.to_doappinkotlin > databases > *. Open and check with
       SQLite and go to browse data > to do table when using todo_database file.
       For reading data. NEED to use SQLite Browser.
     */
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

    /* Needed to manually check data on SQLite database. for DeviceFile explorer to
       data > data > com.example.to_doappinkotlin > databases > *. Open and check with
       SQLite and go to browse data > to do table when using todo_database file.
       For reading data. NEED to use SQLite Browser.
     */
    fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> { Priority.HIGH }
            "Medium Priority" -> { Priority.MEDIUM }
            "Low Priority" -> { Priority.LOW }
            else -> Priority.LOW
        }
    }

}