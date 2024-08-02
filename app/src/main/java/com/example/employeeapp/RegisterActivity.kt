package com.example.employeeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // inside main()
        val textLogin: TextView = findViewById(R.id.textLogin)
        textLogin.setOnClickListener {
            val intentLogin = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intentLogin)
        }
        // end listener

        // Find 4 EditText, Extract Data
        val edEmail: EditText = findViewById(R.id.edEmail)
        val edName : EditText = findViewById(R.id.edName)
        val edPhone : EditText = findViewById(R.id.edPhone)
        val edPassword : EditText = findViewById(R.id.edPassword)

        // Find the Register Button, Add Listener:
        val btnRegister: Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            // Extract Data from the EditTexts
            val user_email = edEmail.text
            val user_name = edName.text
            val user_phone = edPhone.text
            val user_password = edPassword.text

            // Api: String
            val api = "https://Jacobsimiyu.pythonanywhere.com/user_register"

            // Convert Data to JSONObject()
            val data = JSONObject()
            data.put("user_email", user_email.toString())
            data.put("user_name", user_name.toString())
            data.put("user_phone", user_phone.toString())
            data.put("user_password", user_password.toString())

            // Call the post method from ApiHelper()
            val helper = ApiHelper(applicationContext)
            helper.post(api, data)

        }
        // end listener


    }
    // end main()
    // outside
}