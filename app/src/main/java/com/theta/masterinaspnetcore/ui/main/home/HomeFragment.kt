package com.theta.masterinaspnetcore.ui.main.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theta.masterinaspnetcore.Injection

import com.theta.masterinaspnetcore.R
import com.theta.masterinaspnetcore.ui.topic.PostAdapter
import com.theta.masterinaspnetcore.ui.topic.TopicListViewModel

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mViewModel: HomeViewModel
    private lateinit var mAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        initRecyclerView(root)
        initViewModel()

        return root
    }

    private fun initViewModel() {
        val observer = Observer(mAdapter::submitList)
        mViewModel = ViewModelProviders.of(this,
            Injection.provideViewModelFactory(activity!!.application))
            .get(HomeViewModel::class.java)
        val mNews = mViewModel.mNews
        mNews.observe(this, observer)
    }

    private fun initRecyclerView(root: View) {
        mRecyclerView = root.findViewById(R.id.rv_news)
        val layoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.hasFixedSize()
        mAdapter = NewsAdapter()
        mRecyclerView.adapter = mAdapter
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
