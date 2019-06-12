package com.theta.masterinaspnetcore.ui.main.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.theta.masterinaspnetcore.R
import com.theta.masterinaspnetcore.api.RetroClient
import com.theta.masterinaspnetcore.data.RetrofitLiveData
import com.theta.masterinaspnetcore.model.News
import com.theta.masterinaspnetcore.model.PostMedia
import com.theta.masterinaspnetcore.utils.Helpers

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsVH>(diffCallback) {
     lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_list, parent, false)
        return NewsVH(view)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        val news = getItem(position)
        if (news != null){
            holder.bindTo(news)
        }
    }


    class NewsVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mApiService = RetroClient.getDevApi()

        private val mNewsTitle: TextView = itemView.findViewById(R.id.txt_title)
        private val mNewsBody = itemView.findViewById<TextView>(R.id.txt_body)
        private val mReedMore = itemView.findViewById<TextView>(R.id.txt_reed)
        private val mFeaturedImage = itemView.findViewById<ImageView>(R.id.img_featured)

        fun bindTo(item: News) {
            mNewsTitle.text = item.title.rendered
            mNewsBody.text = Helpers.getStringFromHtml(item.content.rendered)

            mReedMore.setOnClickListener {
                openLink(item.link)
            }
        }

        private fun openLink(link: String) {
            itemView.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
    }



    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem
        }
    }
}