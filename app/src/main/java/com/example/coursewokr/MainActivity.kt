package com.example.coursewokr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView




private const val LAST_SELECTED_ITEM="item"
class MainActivity : AppCompatActivity() {

    private lateinit var bottomMenu:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomMenu = findViewById(R.id.bottom_menu)

        bottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.weather -> {
                    val menuFragment = MenuFragment()
                    replaceFragment(menuFragment)
                }
                R.id.about -> {
                    val aboutFragment = AboutFragment()
                    replaceFragment(aboutFragment)
                }
            }
            true
        }

        bottomMenu.selectedItemId = savedInstanceState?.getInt(LAST_SELECTED_ITEM) ?:R.id.weather
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(LAST_SELECTED_ITEM, bottomMenu.selectedItemId)
        super.onSaveInstanceState(outState)
    }




}


