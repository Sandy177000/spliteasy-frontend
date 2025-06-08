package com.example.spliteasy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spliteasy.R
import com.example.spliteasy.adapter.GroupAdapter
import com.example.spliteasy.data.model.Group
import com.example.spliteasy.data.model.User

class GroupsFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var groupAdapter: GroupAdapter
    private lateinit var groupList: List<Group>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_groups, container, false)
        recyclerView = view.findViewById(R.id.groupsRecyclerView)
        val user1 = User(
            uid = "u1",
            name = "Sandesh",
            email = "sandesh@example.com",
            profileImage = null
        )

        val user2 = User(
            uid = "u2",
            name = "Priya",
            email = "priya@example.com",
            profileImage = null
        )

        val user3 = User(
            uid = "u3",
            name = "Ravi",
            email = "ravi@example.com",
            profileImage = null
        )

        val dummyGroups = listOf(
            Group(
                id = "g1",
                name = "Goa Trip",
                members = listOf(user1, user2, user3),
                description = "Trip expenses for Goa",
                createdAt = "2025-06-01T10:00:00Z",
                createdBy = user1,
                totalBalance = 1500.0,
                currency = "INR",
                imageUrl = null
            ),
            Group(
                id = "g2",
                name = "Flat Rent",
                members = listOf(user1, user3),
                description = "Monthly rent split",
                createdAt = "2025-05-01T08:30:00Z",
                createdBy = user3,
                totalBalance = -800.0,
                currency = "INR",
                imageUrl = null
            ),
            Group(
                id = "g1",
                name = "Goa Trip",
                members = listOf(user1, user2, user3),
                description = "Trip expenses for Goa",
                createdAt = "2025-06-01T10:00:00Z",
                createdBy = user1,
                totalBalance = 1500.0,
                currency = "INR",
                imageUrl = null
            ),
            Group(
                id = "g2",
                name = "Flat Rent",
                members = listOf(user1, user3),
                description = "Monthly rent split",
                createdAt = "2025-05-01T08:30:00Z",
                createdBy = user3,
                totalBalance = -800.0,
                currency = "INR",
                imageUrl = null
            ),
            Group(
                id = "g1",
                name = "Goa Trip",
                members = listOf(user1, user2, user3),
                description = "Trip expenses for Goa",
                createdAt = "2025-06-01T10:00:00Z",
                createdBy = user1,
                totalBalance = 1500.0,
                currency = "INR",
                imageUrl = null
            ),
            Group(
                id = "g2",
                name = "Flat Rent",
                members = listOf(user1, user3),
                description = "Monthly rent split",
                createdAt = "2025-05-01T08:30:00Z",
                createdBy = user3,
                totalBalance = -800.0,
                currency = "INR",
                imageUrl = null
            ),
            Group(
                id = "g1",
                name = "Goa Trip",
                members = listOf(user1, user2, user3),
                description = "Trip expenses for Goa",
                createdAt = "2025-06-01T10:00:00Z",
                createdBy = user1,
                totalBalance = 1500.0,
                currency = "INR",
                imageUrl = null
            ),
            Group(
                id = "g2",
                name = "Flat Rent",
                members = listOf(user1, user3),
                description = "Monthly rent split",
                createdAt = "2025-05-01T08:30:00Z",
                createdBy = user3,
                totalBalance = -800.0,
                currency = "INR",
                imageUrl = null
            )
        )
        groupAdapter = GroupAdapter(dummyGroups, ::onClickOfGroupItem)
        recyclerView.adapter = groupAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    fun onClickOfGroupItem(group: Group) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
        // navigate to group details
    }
}