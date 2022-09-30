package com.jamascrorp.testproject.presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jamascrorp.testproject.R

object Util {

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun Fragment.showAction(fragment: Fragment) {
        (fragment.activity as AppCompatActivity).supportActionBar?.show()
    }

    fun Fragment.hideAction(fragment: Fragment) {
        (fragment.activity as AppCompatActivity).supportActionBar?.hide()
    }

    fun Fragment.customAction(fragment: Fragment, view: View) {
        (fragment.activity as AppCompatActivity).supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            customView = view
        }
    }

    fun Fragment.hideBottom(fragment: Fragment) {
        (activity as AppCompatActivity).apply {
            supportActionBar?.hide()
            activity?.findViewById<BottomNavigationView>(R.id.bottom)?.visibility = View.GONE
        }
    }


    fun Fragment.showBottom(fragment: Fragment) {
        (activity as AppCompatActivity).apply {
            supportActionBar?.show()
            activity?.findViewById<BottomNavigationView>(R.id.bottom)?.visibility = View.VISIBLE
        }
    }

    fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}