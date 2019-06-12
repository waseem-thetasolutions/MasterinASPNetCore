package com.theta.masterinaspnetcore.ui.topic

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.theta.masterinaspnetcore.R
import com.theta.masterinaspnetcore.model.Post
import com.theta.masterinaspnetcore.ui.topic.TopicListFragment.OnListFragmentInteractionListener

class PostAdapter(private val mListener: OnListFragmentInteractionListener?) : PagedListAdapter<Post, PostAdapter.PostVH>(diffCallback) {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)

        return PostVH(view).listen { pos, _ ->
            val item = getItem(pos)
            if (item != null){
                mListener?.onListFragmentInteraction(item)
            }
        }
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.mCounter.text = ""+(position+1)
        Log.d("Items",currentList.toString())
        holder.bindTo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }

    class PostVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mCounter: TextView = itemView.findViewById(R.id.txt_counter)
        private val mTopicDone: ImageView = itemView.findViewById(R.id.img_done)
        private val mTopicHeading: TextView = itemView.findViewById(R.id.txt_topic_heading)

        fun bindTo(post: Post?){
            if (post != null) {
                mTopicHeading.text = post.title.rendered
                if (post.isRead){
                    mTopicDone.visibility = View.VISIBLE
                    mCounter.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}