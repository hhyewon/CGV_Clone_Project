package com.example.rp_week5

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rp_week5.databinding.TicketingItemBinding

class CgvAdapter (private val context: Context, private var LocationArrayList: ArrayList<Location>) :
    RecyclerView.Adapter<CgvAdapter.ViewHolder>() {

    var location_dataList: ArrayList<Location> = LocationArrayList

    class ViewHolder(val binding: TicketingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Location) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = TicketingItemBinding.inflate(
            LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.cgvList.text = location_dataList[position].cgv_location
    }

    override fun getItemCount(): Int {
        return location_dataList.size
    }

}