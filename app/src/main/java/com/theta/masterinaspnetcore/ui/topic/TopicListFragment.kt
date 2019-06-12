package com.theta.masterinaspnetcore.ui.topic

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theta.masterinaspnetcore.Injection
import com.theta.masterinaspnetcore.R
import com.theta.masterinaspnetcore.model.Post
import com.theta.masterinaspnetcore.model.PostListResult
import com.theta.masterinaspnetcore.ui.main.study.StudyViewModel

class TopicListFragment : Fragment() {


    companion object {
        fun newInstance() = TopicListFragment()
    }

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mViewModel: TopicListViewModel
    private lateinit var mAdapter: PostAdapter
    private var lessonId: Int = 0
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = TopicListFragmentArgs.fromBundle(requireActivity().intent.extras) //used to receive args sent by sage arguments
            lessonId = args.lessonId
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_topic_list, container, false)

        initRecyclerView(root)
        initViewModel()
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initViewModel() {
        val observer = Observer(mAdapter::submitList)
        mViewModel = ViewModelProviders.of(this,
            Injection.provideViewModelFactory(activity!!.application))
            .get(TopicListViewModel::class.java)
        val mPosts = mViewModel.getPostsByCategory(lessonId).data
        mPosts.observe(this, observer)
    }

    private fun initRecyclerView(root: View) {
        mRecyclerView = root.findViewById(R.id.rv_topic)
        val layoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.hasFixedSize()
        mAdapter = PostAdapter(listener)
        mRecyclerView.adapter = mAdapter
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Post?)
    }

}
