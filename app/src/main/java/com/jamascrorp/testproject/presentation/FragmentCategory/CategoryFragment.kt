package com.jamascrorp.testproject.presentation.FragmentCategory

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamascrorp.testproject.databinding.FragmentCategoryBinding
import com.jamascrorp.testproject.di.DaggerComponent
import com.jamascrorp.testproject.di.module.ContextModule
import com.jamascrorp.testproject.di.module.RetrofitModule
import com.jamascrorp.testproject.presentation.Util.hideKeyboard
import com.jamascrorp.testproject.presentation.recyclerview.adapter.CategoryAdapter
import com.jamascrorp.testproject.presentation.recyclerview.adapter.PagingAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryFragment : Fragment() {

    private var viewBinding: FragmentCategoryBinding? = null
    private val binding get() = viewBinding!!
    private var adapter: CategoryAdapter? = null
    private var adapters: PagingAdapter? = null

    @Inject
    lateinit var viewModel: CategoryFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as com.jamascrorp.testproject.di.Inject).injectCategory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        adapters = PagingAdapter()
        viewBinding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategories()

        getCategory()
        binding.find.addTextChangedListener {
            if (it?.isNotEmpty()!!) {
                lifecycleScope.launch {
                    println("x")
                    viewModel.getNews(it.toString()).flowWithLifecycle(lifecycle).collect {
                        delay(500)
                        adapters?.submitData(it)
                    }
                }
                getSearch()
            } else getCategory()
        }

        binding.find.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                binding.find.hideKeyboard()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun getCategory() {
        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
            adapter = CategoryAdapter(it)
            binding.recycleCategory.adapter = adapter
            binding.recycleCategory.layoutManager =
                GridLayoutManager(activity?.applicationContext, 2)

            adapter!!.select = {
                val action =
                    CategoryFragmentDirections.actionCategoryFragmentToCategoryNewsFragment(args = it)

                findNavController().navigate(action)
            }
        }
    }

    private fun getSearch() {
        binding.recycleCategory.adapter = adapters
        binding.recycleCategory.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        adapters!!.select = {
            val action =
                CategoryFragmentDirections.actionCategoryFragmentToWebViewFragment(
                    it.url!!, it)
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
        adapter = null
        adapters = null
    }
}
