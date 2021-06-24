package com.example.articleapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.articleapplication.adapter.base.BaseAdapter
import com.example.articleapplication.adapter.base.BaseViewHolder
import com.example.articleapplication.databinding.ViewPagerItemBinding
import com.example.articleapplication.extension.load
import com.example.articleapplication.model.Article

class VPArticleAdapter : BaseAdapter<Article>() {

    fun setItems(items: List<Article>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Article, ViewBinding> {
        return VPArticleViewHolder(
            ViewPagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class VPArticleViewHolder(private val binding: ViewPagerItemBinding) :
        BaseViewHolder<Article, ViewPagerItemBinding>(binding) {


        override fun bind(data: Article) {
            binding.tvTitle.text = data.title
            binding.ivImage.load(data.imageUrl!!)
            binding.tvDate.text = data.updatedAt
            binding.tvSummary.text = data.summary
            binding.tvProviders.text = data.events.fold("") { acc, elem ->
                "$acc ${elem.provider!!}, "
            }.dropLast(2)
        }
    }
}