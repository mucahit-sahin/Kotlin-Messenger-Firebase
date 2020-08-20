package com.mucahitsahin.kotlin_chatbox_firebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.fragment.HomeFragment
import com.mucahitsahin.kotlin_chatbox_firebase.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation:BottomNavigationView=findViewById(R.id.bottomNavigationView)

        homeFragment= HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener{

            when(it.itemId){

                R.id.home_navigation ->{
                    homeFragment= HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.profile_navigation ->{
                    profileFragment= ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

            }

            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }



}