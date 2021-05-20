package com.capstone.belinkStore

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.belinkstore.Data
import com.example.belinkstore.Sign
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun setStringArrayPref(context: Context,key:String,values: Data){ //스토어 id 저장하기
    val pref: SharedPreferences =context.getSharedPreferences(key, AppCompatActivity.MODE_PRIVATE)
    val edit: SharedPreferences.Editor = pref.edit()
    edit.putString(key, null)
    val data:String
    val tempJSONObject = JSONObject()
    tempJSONObject.put("signId",values.data.id)
    data = tempJSONObject.toString()

    if(values.data.id.isNotEmpty()) {
        edit.putString(key, data.toString())
    }else{
        edit.putString(key,null)
}
edit.apply()
}


fun getStringArraySaved(context: Context,key: String): String { //스토어 id 가져오기
    val pref : SharedPreferences = context.getSharedPreferences(key,AppCompatActivity.MODE_PRIVATE)
    val json = pref.getString(key,null)
    var data: String = ""
    if(json!=null){
        try{
            val id = JSONObject().getString("signId")
            data = id.toString()

        }catch (e:JSONException){
            e.printStackTrace()
        }
    }

    return data
}

//fun setStringArrayPref(context: Context,key:String,values: Data){ //스토어 id 저장하기
//    val pref: SharedPreferences =context.getSharedPreferences(key, AppCompatActivity.MODE_PRIVATE)
//    val edit: SharedPreferences.Editor = pref.edit()
//    edit.putString(key, null)
//    val dataList= JSONArray()
//    val tempJSONObject = JSONObject()
//    tempJSONObject.put("signId",values.data.id)
//    dataList.put(tempJSONObject)
//
//    if(values.data.id.isNotEmpty()) {
//        edit.putString(key, dataList.toString())
//    }else{
//        edit.putString(key,null)
//    }
//    edit.apply()
//}
//
//
//fun getStringArraySaved(context: Context,key: String): MutableList<String> { //스토어 id 가져오기
//    val pref : SharedPreferences = context.getSharedPreferences(key,AppCompatActivity.MODE_PRIVATE)
//    val json = pref.getString(key,null)
//    val list: MutableList<String> = ArrayList()
//    if(json!=null){
//        try{
//            val temp = JSONArray(json)
//            for(i in 0 until temp.length()){
//                val iObject = temp.getJSONObject(i)
//                val id = iObject.getString("signId")
//                list.add(id)
//            }
//        }catch (e:JSONException){
//            e.printStackTrace()
//        }
//    }
//    return list
//}