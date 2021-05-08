package com.example.testtiket.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtiket.R
import com.example.testtiket.adapter.UserAdapter
import com.example.testtiket.databinding.FragmentMainBinding
import com.example.testtiket.utils.ShowState
import com.example.testtiket.utils.State
import com.example.testtiket.viewmodel.MainViewModel

class MainFragment : Fragment() {
    companion object {
        const val stateId = 1
    }

    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var mainViewModel: MainViewModel
    private var showState = ShowState(stateId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)
        mainBinding.emptyText.text = resources.getString(R.string.empty_state)
        userAdapter = UserAdapter(arrayListOf())
        mainBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }

        mainBinding.searchView.apply {
            queryHint = "Masukkan username github terlebih dahulu ..."
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    mainViewModel.setSearch(query)
                    mainBinding.searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean = false
            })
        }
        observeHome()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mainBinding.root
    }

    private fun observeHome() {
        mainViewModel.searchResult.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.state) {
                    State.SUCCESS -> {
                        resource.data?.let { users ->
                            if (!users.isNullOrEmpty()) {
                                showState.success(mainBinding)
                                userAdapter.setData(users)
                            } else {
                                showState.error(mainBinding, null)
                            }
                        }
                    }
                    State.LOADING -> showState.loading(mainBinding)
                    State.ERROR -> showState.error(mainBinding, it.message)
                }
            }
        })
    }

}