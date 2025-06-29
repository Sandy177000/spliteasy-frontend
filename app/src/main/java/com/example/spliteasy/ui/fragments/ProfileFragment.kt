package com.example.spliteasy.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.spliteasy.R
import com.example.spliteasy.data.local.SessionManager
import com.example.spliteasy.ui.activities.LoginActivity

class ProfileFragment: Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var textName: TextView
    private lateinit var textEmail: TextView
    private lateinit var imageProfile: ImageView
    private lateinit var logoutBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        sessionManager = SessionManager(requireContext())
        textName = view.findViewById(R.id.textName)
        textEmail = view.findViewById(R.id.textEmail)
        imageProfile = view.findViewById(R.id.imageProfile)
        logoutBtn = view.findViewById(R.id.btnLogout)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textName.text = sessionManager.getUserName()
        textEmail.text = sessionManager.getUserEmail()
        logoutBtn.setOnClickListener {
            sessionManager.clearSession()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            // flag is set so that navigation stack is reset.
            // user should not be allowed to visit main activity
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

}