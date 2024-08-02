package com.example.employeeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import org.json.JSONArray


class HomeFragment : Fragment() {
    lateinit var itemList : List<Employee>
    lateinit var recyclerView : RecyclerView
    lateinit var employeeAdapter : EmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        // inside main
        recyclerView = view.findViewById(R.id.recycler_view)
        employeeAdapter = EmployeeAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        fetchEmployees()

        return view
    }
    // outside main
    // function to fetch data(Employee)
    fun fetchEmployees(){
        val api = "https://benlab5.pythonanywhere.com/all_employees"
        val helper = ApiHelper(requireContext())
        helper.get(api, object : ApiHelper.CallBack{
            override fun onSuccess(result: String?) {
                val resultJsonArray = JSONArray(result.toString())
                // convert jsonarrayto a list
                val gson = GsonBuilder().create()
                itemList = gson.fromJson(resultJsonArray.toString(), Array<Employee>::class.java).toList()
                employeeAdapter.setListItems(itemList)
                recyclerView.adapter = employeeAdapter
            }
        })
    }


}