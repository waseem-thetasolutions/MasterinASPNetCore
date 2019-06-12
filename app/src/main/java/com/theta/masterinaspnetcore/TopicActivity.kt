package com.theta.masterinaspnetcore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import com.theta.masterinaspnetcore.model.Post
import com.theta.masterinaspnetcore.ui.topic.TopicDetailFragmentDirections
import com.theta.masterinaspnetcore.ui.topic.TopicListFragment
import com.theta.masterinaspnetcore.ui.topic.TopicListFragmentDirections
import com.theta.masterinaspnetcore.utils.AppConstants

class TopicActivity : AppCompatActivity(), TopicListFragment.OnListFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_activity)

        val lessonId = intent.getIntExtra(AppConstants.ITEM_ID_KEY,0)

    }

    override fun onListFragmentInteraction(item: Post?) {
        if (item != null){
            val action = TopicListFragmentDirections
                .actionTopicListDestToTopicDetailFragment()
            action.topicId = item.id
            findNavController(R.id.nav_host).navigate(action)
        }
    }

}
