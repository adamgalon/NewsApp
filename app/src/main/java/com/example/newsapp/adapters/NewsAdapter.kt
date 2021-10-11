package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivArticleImage: ImageView
        val tvSource: TextView
        val tvTitle: TextView
        val tvDescription: TextView
        val tvPublischedAt: TextView

        init {
            ivArticleImage = view.findViewById(R.id.ivArticleImage)
            tvSource = view.findViewById(R.id.tvSource)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvDescription = view.findViewById(R.id.tvDescription)
            tvPublischedAt = view.findViewById(R.id.tvPublishedAt)
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        Glide.with(holder.itemView).load(article.urlToImage).into(holder.ivArticleImage)
        holder.tvSource.text = article.source.name
        holder.tvTitle.text = article.title
        holder.tvDescription.text = article.description
        holder.tvPublischedAt.text = article.publishedAt
        View.OnClickListener {
            onItemClickListener?.let { it(article) }
        }
        //czy to działa tak samo ?
//        setOnItemClickListener {
//            onItemClickListener?.let { it(article) }
//        }

    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}