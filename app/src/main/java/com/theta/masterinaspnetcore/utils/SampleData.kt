package com.theta.masterinaspnetcore.utils

import com.theta.masterinaspnetcore.model.Category


class SampleData {
    companion object{
        fun getListOfLessons() : List<Category> {
            val listOfLessons = arrayListOf<Category>()
            for (i in 0..5){
                val mCategory = Category(i, "name$i")
                listOfLessons.add(mCategory)
            }

            return listOfLessons
        }
    }
}