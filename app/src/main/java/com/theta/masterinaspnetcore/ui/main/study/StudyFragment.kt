package com.theta.masterinaspnetcore.ui.main.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theta.masterinaspnetcore.Injection
import com.theta.masterinaspnetcore.R


class StudyFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mViewModel: StudyViewModel
    private val mAdapter = LessonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
//        }
        initViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_study, container, false)

        initRecyclerView(root)

        return root
    }

    private fun initViewModel() {
        val observer = Observer(mAdapter::submitList)
        mViewModel = ViewModelProviders.of(this,
            Injection.provideViewModelFactory(activity!!.application))
            .get(StudyViewModel::class.java)
        val mCategories = mViewModel.mCategoriesListResult.data
        mCategories.observe(this, observer)
    }

    private fun initRecyclerView(root: View) {
        mRecyclerView = root.findViewById(R.id.rv_lesson)
        val layoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.hasFixedSize()
        mRecyclerView.adapter = mAdapter
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(): StudyFragment {
            return StudyFragment().apply {
                arguments = Bundle().apply {
                    //                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}