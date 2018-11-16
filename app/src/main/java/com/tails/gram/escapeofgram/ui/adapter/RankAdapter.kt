package com.tails.gram.escapeofgram.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tails.gram.escapeofgram.R
import com.tails.gram.escapeofgram.model.Rank
import kotlinx.android.synthetic.main.ranking_item.view.*

class RankAdapter(private val rankList : ArrayList<Rank>) : RecyclerView.Adapter<RankAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ranking_item, parent, false))

    override fun getItemCount(): Int = rankList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.rank_tv.text = "${position + 1}"
        holder.itemView.rank_name_tv.text = rankList[position].name
        holder.itemView.rank_time_tv.text = rankList[position].time.subSequence(3, rankList[position].time.length)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}