package com.jamascrorp.testproject.presentation.FragmentCategoryNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamascrorp.testproject.databinding.FragmentCategoryNewsBinding
import com.jamascrorp.testproject.di.DaggerComponent
import com.jamascrorp.testproject.di.module.ContextModule
import com.jamascrorp.testproject.di.module.RetrofitModule
import com.jamascrorp.testproject.presentation.recyclerview.adapter.PagingAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryNewsFragment : Fragment() {
    private var viewBinding: FragmentCategoryNewsBinding? = null
    private val binding get() = viewBinding!!

    @Inject
    lateinit var viewModel: CategoryNewsFragmentViewModel

    private var adapter: PagingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as com.jamascrorp.testproject.di.Inject).injectNews(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentCategoryNewsBinding.inflate(layoutInflater)
        adapter = PagingAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cats = CategoryNewsFragmentArgs.fromBundle(requireArguments()).args
        val source = CategoryNewsFragmentArgs.fromBundle(requireArguments()).args1
        if (source == "null") {
            binding.textCategoryNews.text = cats?.replaceFirstChar { it.uppercase() }
            binding.shimm.startShimmer()
            lifecycleScope.launch {
                viewModel.getnewsByCategory(cats!!).flowWithLifecycle(lifecycle).collect {
                    binding.shimm.stopShimmer()
                    adapter?.submitData(it)
                }
            }
        } else {
            lifecycleScope.launch {
                viewModel.getnewsBySource(source!!).flowWithLifecycle(lifecycle).collect {
                    binding.textCategoryNews.text =
                        source.replaceFirstChar { it.uppercase() }.replace("-", " ")
                    binding.shimm.stopShimmer()
                    adapter?.submitData(it)
                }
            }
        }
        bindUI()
    }

    fun bindUI() {
        binding.recycleCategoryNews.layoutManager =
            LinearLayoutManager(activity?.applicationContext)
        binding.recycleCategoryNews.adapter = adapter
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            viewBinding?.shimm?.visibility = View.GONE
        }
        adapter?.select = {
            val action =
                CategoryNewsFragmentDirections.actionCategoryNewsFragmentToWebViewFragment(it.url!!,
                    it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
        adapter = null
    }
}



