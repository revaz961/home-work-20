package com.example.articleapplication.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.setPadding
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.articleapplication.viewmodel.ArticleViewModel
import com.example.articleapplication.R
import com.example.articleapplication.adapter.VPArticleAdapter
import com.example.articleapplication.databinding.ArticleFragmentBinding
import com.example.articleapplication.extension.isVisible
import com.example.articleapplication.network.EndPoint
import com.example.articleapplication.network.ResultHandler

class ArticleFragment : BaseFragment<ArticleFragmentBinding, ArticleViewModel>(
    ArticleFragmentBinding::inflate,
    ArticleViewModel::class.java
) {

    private lateinit var vpAdapter: VPArticleAdapter

    override fun start() {
        init()
        observes()
    }

    private fun init() {
        binding.toolbar.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getResult(EndPoint.SEARCH, newText ?: "")
                return true
            }

        })
        vpAdapter = VPArticleAdapter()
        with(binding.vpArticle) {
            adapter = vpAdapter

        }
        viewModel.getResult(EndPoint.ARTICLE)
    }


    private fun observes() {
        viewModel.liveData.observe(this, {
            when(it){
                is ResultHandler.Success->vpAdapter.setItems(it.data!!)
                is ResultHandler.Loading ->  binding.progress.isVisible(it.loading)
            }

        })
    }

}