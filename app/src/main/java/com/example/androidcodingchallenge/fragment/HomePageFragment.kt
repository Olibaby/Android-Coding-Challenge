package com.example.androidcodingchallenge.fragment

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcodingchallenge.HomePageFeedAdapter
import com.example.androidcodingchallenge.R
import com.example.androidcodingchallenge.viewmodel.HomeViewModel
import com.example.map.base.BaseFragment
import com.example.map.base.observeChange
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private val adapter:HomePageFeedAdapter by lazy { HomePageFeedAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers(homeViewModel)
        observeViewModel()
        updateRecycler()
    }

    private fun observeViewModel() {
        homeViewModel.getHomePageFeedsFromDb().observeChange(viewLifecycleOwner){
            pageFeedsRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            pageFeedsRecycler.adapter = adapter
        }
    }

    private fun updateRecycler() {
        println("home pageee")
        homeViewModel.getHomePageFeeds()
        homeViewModel.getHomePageFeedsResponse.observeChange(viewLifecycleOwner){
            adapter.updateIems(it.page.cards)
            println("${it.page.cards.count()}")
        }
    }

    companion object {
    }
}