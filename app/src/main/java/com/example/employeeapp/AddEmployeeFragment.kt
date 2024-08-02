package com.example.employeeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import org.json.JSONObject


class AddEmployeeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_employee, container, false)
        // inside main

        // find the 5 edittexts based on id
        val edIdNumber: EditText = view.findViewById(R.id.edIdNumber)
        val edUsername : EditText = view.findViewById(R.id.edUsername)
        val edOthers : EditText = view.findViewById(R.id.edOthers)
        val edSalary : EditText = view.findViewById(R.id.edSalary)
        val edDepartment : EditText = view.findViewById(R.id.edDepartment)

        // find the registerEmp btn and assign a listener
        val registerBtn : Button = view.findViewById(R.id.btnRegisterEmployee)
        registerBtn.setOnClickListener {
            // extract data from the edittexts
            val id_number = edIdNumber.text
            val username = edUsername.text
            val others = edOthers.text
            val salary = edSalary.text
            val department = edDepartment.text

            // call the api
            // Api: String
            val api = "https://benlab5.pythonanywhere.com/add_employee"

            // prepare a JSONObject()
            // Convert Data to JSONObject()
            val data = JSONObject()
            data.put("id_number", id_number.toString())
            data.put("username", username.toString())
            data.put("others", others.toString())
            data.put("salary", salary.toString())
            data.put("department", department.toString())


            // implement the post method
            val helper = ApiHelper(requireContext())
            helper.post(api, data)

        }
        // end listener

            return  view

    }
    // end main()
    // outside

}