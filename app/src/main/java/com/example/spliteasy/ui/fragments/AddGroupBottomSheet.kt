package com.example.spliteasy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.spliteasy.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddGroupBottomSheet: BottomSheetDialogFragment() {

    private lateinit var editTextGroupName: EditText
    private lateinit var buttonCreateGroup: Button
    private lateinit var buttonCancelGroup: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.bottomsheet_add_group, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        editTextGroupName = view.findViewById(R.id.editTextGroupName)
        buttonCreateGroup = view.findViewById(R.id.buttonCreateGroup)
        buttonCancelGroup = view.findViewById(R.id.buttonCancelGroup)

        buttonCreateGroup.setOnClickListener {
            Toast.makeText(requireContext(), "create clicked", Toast.LENGTH_SHORT).show()
        }
    }
}