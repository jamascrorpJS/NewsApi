package com.jamascrorp.testproject.presentation.FragmentAccount

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jamascrorp.testproject.databinding.FragmentAccountBinding
import com.jamascrorp.testproject.di.DaggerComponent
import com.jamascrorp.testproject.di.module.ContextModule
import com.jamascrorp.testproject.di.module.RetrofitModule
import com.jamascrorp.testproject.presentation.Util.hideBottom
import javax.inject.Inject

class CreateProfileFragment : Fragment() {
    private var viewBinding: FragmentAccountBinding? = null
    private val binding get() = viewBinding!!
    private var email = ""
    private var password = ""
    private var password1 = ""

    @Inject
    lateinit var viewModel: CreateProfileFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as com.jamascrorp.testproject.di.Inject).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        hideBottom(this)
        viewBinding = FragmentAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cls.setOnClickListener {
            validate()
        }
    }

    fun validate() {
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString()
        password1 = binding.password1.text.toString()

        if (password != password1) {
            binding.password.error = "Password"
        } else if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password1)) {
            binding.password.error = "Enter password"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Enter email"
            println("password")
        } else viewModel.signUp(email, password)
    }

}