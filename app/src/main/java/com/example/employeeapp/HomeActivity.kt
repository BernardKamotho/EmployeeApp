package com.example.employeeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // INSIDE MAIN FUNCTION
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> { replaceFragment(HomeFragment()) }
                R.id.profile -> { replaceFragment(ProfileFragment()) }
                R.id.add -> { replaceFragment(AddEmployeeFragment()) }
                R.id.cart -> { replaceFragment(CartFragment()) }

            }
            true
        }
        // end onItemSelected()
        replaceFragment(HomeFragment())

    }
    // OUTSIDE MAIN FUNCTION
    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
    // end function

}