package com.cszabo.recyclerviewsharedprefs

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loggingTag = getString(R.string.LOGGING_TAG)
        Log.i(loggingTag, getString(R.string.MainActivityLaunchMessage))

        //val arrayList = ArrayList<Model>()
        var arrayList: ArrayList<Model> = arrayListOf()

        //save array list into SharedPreferences
        btnSetPreferences.setOnClickListener {view ->
            val gson = Gson()
            val prefs = getSharedPreferences("com.cszabo.recyclerviewsharedprefs", Context.MODE_PRIVATE)
            Log.i(loggingTag, getString(R.string.ButtonClicked))

            val editor = prefs.edit()
            val json = gson.toJson(arrayList)
            editor.putString("Model", json)
            editor.apply()

            Log.i(loggingTag, getString(R.string.SAVED_MESSAGE))
        }

        //load data from SharedPreferences into arrayList and update RecyclerView
        btnLoadPreferences.setOnClickListener { view ->
            val gson = Gson()
            val prefs = getSharedPreferences("com.cszabo.recyclerviewsharedprefs", Context.MODE_PRIVATE)
            Log.i(loggingTag, getString(R.string.ButtonClicked))
            Log.i(loggingTag, "the set contains ${arrayList.size} elements")


            if (prefs.contains("Model")){
                val json = prefs.getString("Model",null)
                val type = object : TypeToken<ArrayList<Model>>() {}.type
                arrayList = gson.fromJson(json, type)
            }

            val mainAdapter = MainAdapter(arrayList, this)

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = mainAdapter
        }

        //Add new item with description to array, with automatic keyboard closer after button was presser
        btnAdd.setOnClickListener {
            Log.i(loggingTag, "The item was added")
            Log.i(loggingTag, "New set size is  ${arrayList.size} elements")
            val gson = Gson()
            val prefs = getSharedPreferences("com.cszabo.recyclerviewsharedprefs", Context.MODE_PRIVATE)
            arrayList.add(Model(etTaskEntry.text.toString(), etTaskDescription.text.toString(), R.drawable.ic_launcher_foreground))
            closeSoftKeyboard(this,etTaskEntry)

        }

        //clear array list
        btnDel.setOnClickListener {
            Log.i(loggingTag, "ArrayList was cleared")
            arrayList.clear()

            val mainAdapter = MainAdapter(arrayList, this)

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = mainAdapter

        }


        val mainAdapter = MainAdapter(arrayList, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mainAdapter
    }
}

//function to automatically close keyboard after pressing add button, original code: https://www.codegrepper.com/code-examples/kotlin/hide+keyboard+on+button+click+android+kotlin
private fun closeSoftKeyboard(context: Context, v: View) {
    val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    iMm.hideSoftInputFromWindow(v.windowToken, 0)
    v.clearFocus()
}
