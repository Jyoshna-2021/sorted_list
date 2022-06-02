package com.example.sortedlist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var mAdapter: RecyclerViewAdapter? = null
    var Addbutton: Button? = null
    var GetValue: EditText? = null
    var ListElements = arrayOf("apple","android","ball","c","c++","cat","database","hen","ice",
        "java","jack","json","lemon",)
    var coordinatorLayout: CoordinatorLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)

        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                mAdapter!!.removeItem(position)
                val snackbar = Snackbar
                    .make(
                        coordinatorLayout!!,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
               snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(recyclerView)

        val ListElementsArrayList: MutableList<String> = ArrayList(Arrays.asList(*ListElements))

        Addbutton = findViewById<View>(R.id.button1) as Button
        GetValue = findViewById<View>(R.id.editText1) as EditText
        mAdapter = RecyclerViewAdapter(ListElementsArrayList as ArrayList<String>)
        recyclerView!!.adapter = mAdapter
        Addbutton!!.setOnClickListener {
            ListElementsArrayList.add(GetValue!!.text.toString())
            ListElementsArrayList.sortBy { it.toUpperCase() }
            mAdapter!!.notifyDataSetChanged()
        }
    }
}

