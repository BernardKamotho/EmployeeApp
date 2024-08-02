package com.example.employeeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class EmployeeAdapter(var context: Context) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    var itemList: List<Employee> = listOf() // empty by default

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    // 1. onCreateViewHolder -> Used to call the single item file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_employee, parent, false)
        return ViewHolder(view)
    }

    // 2. getItemCount -> Return the number of items on the recycler View
    override fun getItemCount(): Int {
        return itemList.size
    }


    // 3. onBindViewHolder -> Used to bind(attach) data to the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // find the views from single item



        val tvUsername : TextView = holder.itemView.findViewById(R.id.username)
        val tvDepartment : TextView = holder.itemView.findViewById(R.id.department)
        val tvSalary : TextView = holder.itemView.findViewById(R.id.salary)

        // bind data to the views
        val employeeModel = itemList[position]


        tvUsername.text = "Name: " + employeeModel.username
        tvDepartment.text = "Department: " + employeeModel.department
        tvSalary.text = "Salary: " + employeeModel.salary


        holder.itemView.setOnClickListener {
            // Shared Preferences: A way to store data that can be used on another Activity
            // id_number, username, others, salary, department
            // In SP, data is also stored in a key-value pair
            val id_number = employeeModel.id_number
            val username = employeeModel.username
            val others = employeeModel.others
            val salary = employeeModel.salary
            val department = employeeModel.department

            // Storing data on the SharedPreferences
            // Create the SP
            val sharedPrefrences = context.getSharedPreferences("storage", Context.MODE_PRIVATE)
            // create an editor
            val editor = sharedPrefrences.edit()
            editor.putString("id_number", id_number.toString())
            editor.putString("username", username.toString())
            editor.putString("others", others.toString())
            editor.putString("salary", salary.toString())
            editor.putString("department", department.toString())
            editor.apply()

            val intent = Intent(context, SingleEmployeeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }

    fun setListItems(data: List<Employee>){
        itemList = data
        notifyDataSetChanged()
    }
}