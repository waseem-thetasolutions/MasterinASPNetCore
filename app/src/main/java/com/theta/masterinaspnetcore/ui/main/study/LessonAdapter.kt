package com.theta.masterinaspnetcore.ui.main.study

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.theta.masterinaspnetcore.R
import com.theta.masterinaspnetcore.TopicActivity
import com.theta.masterinaspnetcore.model.Category
import com.theta.masterinaspnetcore.utils.AppConstants
import com.theta.masterinaspnetcore.utils.AppConstants.LessonStatus

class LessonAdapter : PagedListAdapter<Category, LessonAdapter.LessonVH>(diffCallback) {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonVH {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)

        return LessonVH(view).listen { pos, _ ->
            val item = getItem(pos)
            if (item != null){
                openTopicListActivity(item.id)
            }
        }
    }

    private fun openTopicListActivity(itemId: Int){
        val intent = Intent(mContext, TopicActivity::class.java)
        intent.putExtra(AppConstants.ITEM_ID_KEY,itemId)
        mContext.startActivity(intent)
    }

    override fun onBindViewHolder(holder: LessonVH, position: Int) {
        holder.mCounter.text = ""+(position+1)
        holder.bindTo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem == newItem
        }
    }

    class LessonVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mCounter: TextView = itemView.findViewById(R.id.txt_counter)
        private val mLessonDone: ImageView = itemView.findViewById(R.id.img_done)
        private val mLessonHeading: TextView = itemView.findViewById(R.id.txt_lesson_heading)
        private val mLessonProgress: ProgressBar = itemView.findViewById(R.id.lesson_progress)

        fun bindTo(category: Category?){
            if (category != null) {
                mLessonHeading.text = category.name

                when(category.status){
                    LessonStatus.NotClicked.value -> {
                        mLessonProgress.visibility = View.INVISIBLE
                    }
                    LessonStatus.InProgress.value -> {
                        mLessonProgress.visibility = View.VISIBLE
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mLessonProgress.setProgress(category.progress,true)
                        }else{
                            mLessonProgress.progress = category.progress
                        }
                    }
                    LessonStatus.Complete.value -> {
                        mLessonProgress.visibility = View.INVISIBLE
                        mLessonDone.visibility = View.VISIBLE
                    }
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