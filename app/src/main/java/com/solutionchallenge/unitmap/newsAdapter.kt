package com.solutionchallenge.unitmap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionchallenge.unitmap.databinding.RecyclerRowNewsBinding
import com.squareup.picasso.Picasso

class newsAdapter(private val news_list: ArrayList<NewsItems>) :
    RecyclerView.Adapter<newsAdapter.NewsHolder>() {
    class NewsHolder(val binding: RecyclerRowNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding =
            RecyclerRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int {
        println("${news_list.size}")
        return news_list.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        //görsel alma
        Picasso.get().load(news_list[position].downloadUrl)
            .into(holder.binding.imageRecyclerRow1)
        println("calıştı")
    }
}