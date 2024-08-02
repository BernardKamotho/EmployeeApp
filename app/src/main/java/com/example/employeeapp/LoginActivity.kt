package com.example.employeeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // inside main
        // Find textRegister and intent(direct) to RegisterActivity
        val textRegister: TextView = findViewById(R.id.textRegister)
        textRegister.setOnClickListener {
            val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intentRegister)
        }
        // end listener

        // Create an Activity: HomeActivity
        // Authentication Process
        // Step1: Capture the 2 EditTexts
        val edEmail : EditText = findViewById(R.id.edEmail)
        val edPassword: EditText = findViewById(R.id.edPassword)

        // Step2: Get the Login Button and Provide Listener
        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            // Step3: Capture data from the EditText

            val user_email = edEmail.text
            val user_password = edPassword.text

            val api = "https://benlab5.pythonanywhere.com/user_login"
            val data = JSONObject()
            data.put("user_email", user_email.toString())
            data.put("user_password", user_password.toString())

            val helper = ApiHelper(applicationContext)
            helper.get_login(api, data, object : ApiHelper.CallBack{
                override fun onSuccess(result: String?) {
                    val resultObj = JSONObject(result.toString())
                    if(resultObj!!.has("user")){
                        Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                        val intentHome = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intentHome)
                    }

                    else{
                        Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }

                }
            })


        }
        // end listener

    }
    // end main()
    // outside
}