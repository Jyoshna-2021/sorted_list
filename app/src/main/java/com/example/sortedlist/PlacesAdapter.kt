package com.example.sortedposition



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PlacesAdapter :
    RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.placeName == newItem.placeName
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {

        return PlacesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val place = differ.currentList[position]
        holder.bind(place)


    }

    inner class PlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val tvPlaceName: TextView = itemView.findViewById(R.id.tvPlaceName)

        fun bind(place: Place) {
            //check for null
            tvPlaceName.text = place.placeName

        }

    }

}

