package com.jamascrorp.testproject.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.databinding.FragmentBinding
import com.jamascrorp.testproject.presentation.Util.hideAction
import com.jamascrorp.testproject.presentation.Util.showAction

class Fragment : Fragment() {

    private var viewBinding: FragmentBinding? = null

    private val binding get() = viewBinding!!
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAction(this)
        if (firebaseAuth.currentUser != null) {
            val email = firebaseAuth.currentUser
            binding.email.text = email?.email.toString()
        } else binding.email.visibility = View.GONE
        binding.cls.setOnClickListener {
            firebaseAuth.signOut()
            findNavController().navigate(R.id.action_fragment_to_authorizationFragment)
        }
    }

    override fun onStop() {
        super.onStop()
        showAction(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}