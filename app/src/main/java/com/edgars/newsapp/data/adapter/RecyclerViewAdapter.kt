package com.edgars.newsapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.edgars.newsapp.R
import com.edgars.newsapp.data.vo.Article
import com.edgars.newsapp.ui.mainfragment.MainFragmentDirections

class RecyclerViewAdapter(private val articlesList: List<Article>) : RecyclerView.Adapter<RecyclerViewAdapter.MainViewHolder>() {

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(article: Article) {
            val name = itemView.findViewById<TextView>(R.id.item_title)
            val image = itemView.findViewById<ImageView>(R.id.item_image)

            name.text = article.title
            image.load(article.urlToImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = articlesList[position]

        holder.bindData(articlesList[position])

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailsFragment(
                    title = item.title,
                    description = item.description,
                    imageUrl = item.urlToImage,
                    source = item.source.name,
                    date = item.publishedAt,
                    url = item.url
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}
