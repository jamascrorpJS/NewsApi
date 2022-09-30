package com.jamascrorp.testproject.presentation.FragmentAuthorization

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.databinding.FragmentAuthorizationBinding
import com.jamascrorp.testproject.presentation.Util.hideBottom
import com.jamascrorp.testproject.presentation.Util.showBottom

class AuthorizationFragment : Fragment() {

    private var viewBinding: FragmentAuthorizationBinding? = null
    private val binding get() = viewBinding!!
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        hideBottom(this)
        viewBinding = FragmentAuthorizationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cls.setOnClickListener {
            validateData()
        }
        binding.textcl.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_accountFragment)
        }
        checkUser()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).apply {
            supportActionBar?.show()
            activity?.findViewById<BottomNavigationView>(R.id.bottom)?.visibility = View.VISIBLE
        }
    }

    fun validateData() {
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Enter email"
        } else if (TextUtils.isEmpty(password)) {
            binding.password.error = "Enter password"
        } else firebaseLogin()
    }

    fun firebaseLogin() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser?.email
                println(email)
                findNavController().navigate(R.id.action_authorizationFragment_to_categoryFragment)

            }
            .addOnFailureListener {
                Toast.makeText(activity?.applicationContext, "Fail", Toast.LENGTH_SHORT).show()
            }
    }

    fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            findNavController().navigate(R.id.action_authorizationFragment_to_categoryFragment)
            showBottom(this)
        } else {
            binding.cls.setOnClickListener { validateData() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewBinding = null
    }
}