package com.jamascrorp.testproject.presentation.FragmentBookmarks

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.testproject.databinding.FragmentBookmarksBinding
import com.jamascrorp.testproject.di.DaggerComponent
import com.jamascrorp.testproject.di.module.ContextModule
import com.jamascrorp.testproject.di.module.RetrofitModule
import com.jamascrorp.testproject.presentation.Util.hideAction
import com.jamascrorp.testproject.presentation.Util.showAction
import com.jamascrorp.testproject.presentation.recyclerview.adapter.BookmarksAdapter
import javax.inject.Inject

class BookmarksFragment : Fragment() {

    private var viewBinding: FragmentBookmarksBinding? = null
    private val binding get() = viewBinding!!

    @Inject
    lateinit var viewModel: BookmarksViewModel
    private var adapter: BookmarksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as com.jamascrorp.testproject.di.Inject).injectBookmarks(this)
        super.onCreate(savedInstanceState)
        hideAction(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentBookmarksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        adapter = BookmarksAdapter()
        hideAction(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAll().observe(viewLifecycleOwner) {

            if (it.isEmpty()) {
                binding.text.apply {
                    text = "Bookmarks is Empty"
                    textSize = 17F
                    visibility = View.VISIBLE
                    gravity = Gravity.CENTER
                }
            } else {
                binding.text.visibility = View.GONE
                adapter?.submitList(it)

                binding.recycleBookmarks.adapter = adapter
                binding.recycleBookmarks.layoutManager =
                    LinearLayoutManager(activity?.applicationContext,
                        LinearLayoutManager.VERTICAL,
                        false)

                adapter!!.select = {
                    val action =
                        BookmarksFragmentDirections.actionBookmarksFragmentToWebViewFragment1(it)
                    findNavController().navigate(action)
                    println(it)
                }
            }

        }
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter?.currentList?.get(viewHolder.bindingAdapterPosition)
                viewModel.delete(item!!)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recycleBookmarks)
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