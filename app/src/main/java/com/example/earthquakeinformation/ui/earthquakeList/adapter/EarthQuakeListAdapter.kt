package com.example.earthquakeinformation.ui.earthquakeList.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakeinformation.R
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.databinding.ItemEarthquakeBinding

class EarthQuakeListAdapter(
    val listener: EarthquakeListeners
): RecyclerView.Adapter<EarthQuakeListAdapter.EarthQuakeViewHolder>() {

    interface EarthquakeListeners{
        fun onSelectEarthquake(earthquake: Earthquake)
    }

    private val earthquakeList: MutableList<Earthquake> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EarthQuakeListAdapter.EarthQuakeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EarthQuakeViewHolder(
            layoutInflater.inflate(R.layout.item_earthquake, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: EarthQuakeListAdapter.EarthQuakeViewHolder,
        position: Int
    ) = holder.bind(earthquakeList[position])

    override fun getItemCount(): Int = earthquakeList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateEarthquakeList(newEarthquakeList: List<Earthquake>){
        earthquakeList.clear()
        earthquakeList.addAll(newEarthquakeList)
        notifyDataSetChanged()
    }

    inner class EarthQuakeViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemEarthquakeBinding.bind(view)

        fun bind(earthquake: Earthquake){

            binding.itemEarthquakeTitle.text = earthquake.title
            binding.itemEarthquakeMag.text = itemView.context.getString(
                R.string.earthquake_magnitude, earthquake.mag.toString()
            )
            binding.itemEarthquakeLocation.text = itemView.context.getString(
                R.string.earthquake_location, earthquake.place
            )

            binding.root.setOnClickListener {
                listener.onSelectEarthquake(earthquake)
            }
        }
    }
}