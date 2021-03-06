package com.example.sortedlist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var placesList = arrayListOf<Place>()
    private lateinit var placesAdapter: PlacesAdapter
    private lateinit var rvPlaces: RecyclerView
    private lateinit var fabAddItem: Button
    private lateinit var getValue: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        getValue = findViewById(R.id.edit_text)
        fabAddItem = findViewById(R.id.fabAddItem)
        fabAddItem.setOnClickListener {
            val names = getValue.text.toString()
            val list = ArrayList<Place>(placesList)
            if (names.isEmpty()) {
                Toast.makeText(this, "enter an item", Toast.LENGTH_LONG).show()
            } else {
                list.add((Place(" $names")))
                getValue.text.clear()
            }
            list.sortBy { it.placeName.uppercase() }
            placesAdapter.differ.submitList(list)
            rvPlaces.smoothScrollToPosition(rvPlaces.adapter!!.itemCount)
            placesList = list
        }
    }

    private fun initRecyclerView() {
        rvPlaces = findViewById(R.id.rvPlaces)
        placesAdapter = PlacesAdapter()
        rvPlaces.layoutManager = LinearLayoutManager(this)
        rvPlaces.adapter = placesAdapter
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvPlaces)
        placesAdapter.differ.submitList(placesList)
    }

    private val itemTouchHelperCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.d(TAG, "onSwiped: placesList ${placesList.size}")
                val place = placesList[viewHolder.bindingAdapterPosition]
                val list = ArrayList<Place>(placesList)
                list.remove(place)
                placesList = list
                placesAdapter.differ.submitList(list)
            }
        }
}


