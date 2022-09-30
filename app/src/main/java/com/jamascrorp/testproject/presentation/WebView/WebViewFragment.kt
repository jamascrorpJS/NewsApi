package com.jamascrorp.testproject.presentation.WebView

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.databinding.FragmentWebviewBinding
import com.jamascrorp.testproject.presentation.Util.hideAction
import com.jamascrorp.testproject.presentation.Util.showAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebViewFragment : Fragment() {

    private var viewBinding: FragmentWebviewBinding? = null
    private val binding get() = viewBinding!!
    private var redirect: Boolean = false
    private var completely: Boolean = true

    @Inject
    lateinit var viewModel: WebviewViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity?.application as com.jamascrorp.testproject.di.Inject).inject(this)
        hideAction(this)
        viewBinding = FragmentWebviewBinding.inflate(layoutInflater)
        val toolbar = binding.toolbar1
        toolbar.setNavigationIcon(R.drawable.ic_x)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args1 = WebViewFragmentArgs.fromBundle(requireArguments()).args
        var args = ""
        val model = WebViewFragmentArgs.fromBundle(requireArguments()).model
        if (args1.contains("http:")) {
            args = args1.replace("http:", "https:")
        } else args = args1

        println(args)
        binding.webview.apply {
            webChromeClient
            webViewClient = (object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?,
                ): Boolean {
                    if (!completely) {
                        redirect = true
                    }
                    completely = false
                    if (viewBinding !== null) {
                        binding.webview.loadUrl(args)
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    return true
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    completely = false
                    viewBinding?.progressbar?.visibility = View.GONE
                }
            })
                loadUrl(args)
            settings.setSupportZoom(true)
            settings.loadWithOverviewMode = true
            settings.databaseEnabled = true
            settings.domStorageEnabled = true
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        }
        CoroutineScope(Dispatchers.IO).launch {
            binding.image.isChecked = viewModel.exists(args)
        }
        binding.image.setOnClickListener {
            val savedText = SavedText()
            if (binding.image.isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.save(model!!, savedText)
                }
                Toast.makeText(activity?.applicationContext, "Added", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.delete(args)
                }
                Toast.makeText(activity?.applicationContext, "Destroy", Toast.LENGTH_SHORT).show()
            }
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