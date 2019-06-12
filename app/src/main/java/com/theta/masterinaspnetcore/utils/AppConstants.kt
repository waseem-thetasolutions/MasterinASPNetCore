package com.theta.masterinaspnetcore.utils

class AppConstants {
    companion object{
        const val ITEM_ID_KEY = "lessonId"
    }

    enum class LessonStatus(val value: Int){
        NotClicked(0),
        InProgress(1),
        Complete(2)
    }
}