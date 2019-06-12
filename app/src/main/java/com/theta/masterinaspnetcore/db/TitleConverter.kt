package com.theta.masterinaspnetcore.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.theta.masterinaspnetcore.model.Content
import com.theta.masterinaspnetcore.model.Title

class TitleConverter {

    @TypeConverter
    fun stringToObject(string: String): Title = Gson().fromJson(string,Title::class.java)

    @TypeConverter
    fun objectToString(title: Title): String = Gson().toJson(title)

    @TypeConverter
    fun stringToContent(string: String): Content = Gson().fromJson(string, Content::class.java)

    @TypeConverter
    fun ContentToString(body: Content): String = Gson().toJson(body)

    @TypeConverter
    fun listToSting(list: List<Int>) : String = Gson().toJson(list)

    @TypeConverter
    fun stringToList(string: String) : List<Int> = Gson().fromJson(string, Array<Int>::class.java).asList()
}