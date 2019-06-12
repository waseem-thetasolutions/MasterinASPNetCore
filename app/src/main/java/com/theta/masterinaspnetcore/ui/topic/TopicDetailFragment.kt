package com.theta.masterinaspnetcore.ui.topic

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.lifecycle.Observer

import com.theta.masterinaspnetcore.R
import com.theta.masterinaspnetcore.model.Post

class TopicDetailFragment : Fragment() {
    private var topicId: Int? = null
    private lateinit var viewModel: TopicDetailViewModel

    private lateinit var mWebView: WebView
    private lateinit var mTitle: TextView

    companion object {
        fun newInstance() = TopicDetailFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = TopicDetailFragmentArgs.fromBundle(it)
            topicId = args.topicId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_topic_detail, container, false)
        mWebView = root.findViewById(R.id.web_view)
        mTitle = root.findViewById(R.id.txt_title)
//        topicId?.let {
//            val data = viewModel.getPostById(it)
//            mTitle.text = data.title.rendered
//            mWebView.loadData(data.content.rendered,"text/html", "UTF-8")
//        }

        initViewModel()

        return root
    }

    fun initViewModel(){
        val noteObserver = Observer<Post>{
            mTitle.text = it.title.rendered
            mWebView.loadData(it.content.rendered,"text/html", "UTF-8")
//            it.isRead = true
//            viewModel.savePost(it)
        }

        viewModel = ViewModelProviders.of(this)
            .get(TopicDetailViewModel::class.java)

        viewModel.mPost.observe(this,noteObserver)

        if (topicId !=null){
            viewModel.getPostById(topicId!!)
        }
    }

}
