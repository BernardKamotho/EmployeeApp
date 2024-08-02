package com.example.employeeapp

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SingleEmployeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_single_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // inside
        // 5 Views based on ids
        val tvIdNumber : TextView = findViewById(R.id.id_number)
        val tvUsername : TextView = findViewById(R.id.username)
        val tvOthers : TextView = findViewById(R.id.others)
        val tvSalary : TextView = findViewById(R.id.salary)
        val tvDepartment : TextView = findViewById(R.id.department)

        // get the SharedPreferences
        val sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE)
        // Get data from the Preferences
        val id_number = sharedPreferences.getString("id_number", "")
        val username = sharedPreferences.getString("username", "")
        val others = sharedPreferences.getString("others", "")
        val salary = sharedPreferences.getString("salary", "")
        val department = sharedPreferences.getString("department", "")

        // Binding data
        tvIdNumber.text = "Employee ID: " + id_number.toString()
        tvUsername.text = "Employee Name : " + username.toString()
        tvOthers.text = "Employee Other Name : " + others.toString()
        tvSalary.text = "Employee Salary: " + salary.toString()
        tvDepartment.text = "Employee Dept: " + department.toString()


    }
    // outside
}