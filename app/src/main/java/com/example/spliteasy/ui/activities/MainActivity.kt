package com.example.spliteasy.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.spliteasy.R
import com.example.spliteasy.ui.fragments.ActivityFragment
import com.example.spliteasy.ui.fragments.FriendsFragment
import com.example.spliteasy.ui.fragments.GroupsFragment
import com.example.spliteasy.ui.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    private var groupsFragment = GroupsFragment()
    private var activityFragment = ActivityFragment()
    private var friendsFragment = FriendsFragment()
    private var profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // initialize
        openFragment(groupsFragment)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_groups -> openFragment(groupsFragment)
                R.id.navigation_friends -> openFragment(friendsFragment)
                R.id.navigation_activity -> openFragment(activityFragment)
                R.id.navigation_profile -> openFragment(profileFragment)
            }
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}