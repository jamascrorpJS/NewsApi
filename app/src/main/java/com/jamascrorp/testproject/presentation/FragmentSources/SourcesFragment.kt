package com.jamascrorp.testproject.presentation.FragmentSources

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jamascrorp.testproject.databinding.FragmentSourcesBinding
import com.jamascrorp.testproject.di.DaggerComponent
import com.jamascrorp.testproject.di.module.ContextModule
import com.jamascrorp.testproject.di.module.RetrofitModule
import com.jamascrorp.testproject.presentation.Util.hideKeyboard
import com.jamascrorp.testproject.presentation.recyclerview.adapter.SourcesAdapter
import javax.inject.Inject

class SourcesFragment : Fragment() {

    private var viewBinding: FragmentSourcesBinding? = null
    private val binding get() = viewBinding!!

    @Inject
    lateinit var viewModel: SourcesFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        (activity?.application as com.jamascrorp.testproject.di.Inject).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentSourcesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSource().observe(viewLifecycleOwner) { q ->
            val adapter = SourcesAdapter(q)
            binding.recycleSources.adapter = adapter
            binding.recycleSources.layoutManager =
                GridLayoutManager(activity?.applicationContext, 2)
            adapter.select = {
                val action =
                    SourcesFragmentDirections.actionSourcesFragmentToCategoryNewsFragment(args1 = it)
                findNavController().navigate(action)
            }
        }
        binding.find.addTextChangedListener { e ->
            if (e?.isNotEmpty()!!) {
                viewModel.getSource().observe(viewLifecycleOwner) { q ->
                    val adapter = SourcesAdapter(q.filter {
                        it.id.replaceFirstChar { it.lowercase() }.contains(e?.trim()!!)
                    })
                    binding.recycleSources.adapter = adapter
                    binding.recycleSources.layoutManager =
                        GridLayoutManager(activity?.applicationContext, 2)
                    adapter.select = {
                        val action =
                            SourcesFragmentDirections.actionSourcesFragmentToCategoryNewsFragment(
                                args1 = it)
                        findNavController().navigate(action)
                    }
                }
            }
        }
        binding.find.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                binding.find.hideKeyboard()
                return@OnKeyListener true
            }
            false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}
