package com.jera.searchapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jera.searchapplication.R
import com.jera.searchapplication.base.BaseViewHolder
import com.jera.searchapplication.data.model.Track

class SearchAdapter(private val context: Context , private val tracksList: List<Track>):
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.tracks_row, parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
         is SearchViewHolder ->   holder.bind(tracksList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return tracksList.size
    }

    inner class SearchViewHolder(itemView: View): BaseViewHolder<Track>(itemView){
        override fun bind(item: Track, position: Int) {
            itemView.findViewById<TextView>(R.id.artist_name_tv).text = item.artistName
            itemView.findViewById<TextView>(R.id.track_name_tv).text = item.trackName
            itemView.findViewById<TextView>(R.id.release_date_tv).text = item.releaseDate
            itemView.findViewById<TextView>(R.id.genre_name_tv).text = item.primaryGenreName
            itemView.findViewById<TextView>(R.id.track_price_tv).text = item.trackPrice.toString()

        }
    }

}