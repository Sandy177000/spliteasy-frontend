package com.example.spliteasy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spliteasy.R
import com.example.spliteasy.adapter.GroupAdapter
import com.example.spliteasy.data.model.Group
import com.example.spliteasy.data.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GroupsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabMain: FloatingActionButton
    private lateinit var fabAddGroup: FloatingActionButton
    private lateinit var fabAddExpense: FloatingActionButton
    private lateinit var fabGroupLayout: LinearLayout
    private lateinit var fabExpenseLayout: LinearLayout

    private var isFabMenuOpen = false
    private lateinit var groupAdapter: GroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_groups, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.groupsRecyclerView)
        setupRecyclerView()

        fabMain = view.findViewById(R.id.addGroupFab)
        fabAddGroup = view.findViewById(R.id.fabAddGroup)
        fabAddExpense = view.findViewById(R.id.fabAddExpense)
        fabGroupLayout = view.findViewById(R.id.fabGroupLayout)
        fabExpenseLayout = view.findViewById(R.id.fabExpenseLayout)
        setupFabMenu()
    }

    private fun setupRecyclerView() {
        val user1 = User("u1", "Sandesh", "sandesh@example.com", null)
        val user2 = User("u2", "Priya", "priya@example.com", null)
        val user3 = User("u3", "Ravi", "ravi@example.com", null)

        val dummyGroups = listOf(
            Group("g1", "Goa Trip", listOf(user1, user2, user3), "Trip expenses for Goa",
                "2025-06-01T10:00:00Z", user1, 1500.0, "INR", null),
            Group("g2", "Flat Rent", listOf(user1, user3), "Monthly rent split",
                "2025-05-01T08:30:00Z", user3, -800.0, "INR", null)
        )

        groupAdapter = GroupAdapter(dummyGroups, ::onClickOfGroupItem)
        recyclerView.adapter = groupAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupFabMenu() {
        fabMain.setOnClickListener {
            if (!isFabMenuOpen) {
                fabGroupLayout.visibility = View.VISIBLE
                fabExpenseLayout.visibility = View.VISIBLE
                fabMain.animate().rotation(45f).setDuration(200).start()
                isFabMenuOpen = true
            } else {
                fabGroupLayout.visibility = View.GONE
                fabExpenseLayout.visibility = View.GONE
                fabMain.animate().rotation(0f).setDuration(200).start()
                isFabMenuOpen = false
            }
        }

        fabAddGroup.setOnClickListener {

            closeFabMenu()
        }

        fabAddExpense.setOnClickListener {
            Toast.makeText(requireContext(), "Add Expense Clicked", Toast.LENGTH_SHORT).show()
            closeFabMenu()
        }
    }


    private fun closeFabMenu() {
        fabGroupLayout.visibility = View.GONE
        fabExpenseLayout.visibility = View.GONE
        fabMain.animate().rotation(0f).setDuration(200).start()
        isFabMenuOpen = false
    }

    private fun onClickOfGroupItem(group: Group) {
        Toast.makeText(requireContext(), "Clicked on ${group.name}", Toast.LENGTH_SHORT).show()
    }
}
