package com.example.testtiket.utils

import android.view.View
import com.example.testtiket.databinding.FragmentMainBinding

class ShowState(private val stateId: Int) {

    fun loading(mainBinding: FragmentMainBinding?) {
        when (stateId) {
            1 -> {
                mainBinding?.emptyText?.visibility = View.GONE
                mainBinding?.progressBar?.visibility = View.VISIBLE
                mainBinding?.recyclerview?.visibility = View.GONE
            }
        }
    }

    fun success(mainBinding: FragmentMainBinding?) {
        when (stateId) {
            1 -> {
                mainBinding?.emptyText?.visibility = View.GONE
                mainBinding?.progressBar?.visibility = View.GONE
                mainBinding?.recyclerview?.visibility = View.VISIBLE
            }
        }
    }

    fun error(
        mainBinding: FragmentMainBinding?,
        message: String?
    ) {
        when (stateId) {
            1 -> {
                mainBinding?.emptyText?.visibility = View.VISIBLE
                mainBinding?.emptyText?.text = message ?: "User tidak ditemukan"
                mainBinding?.progressBar?.visibility = View.GONE
                mainBinding?.recyclerview?.visibility = View.GONE
            }
        }
    }
}