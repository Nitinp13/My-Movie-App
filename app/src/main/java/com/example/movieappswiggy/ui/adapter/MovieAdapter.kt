package com.example.movieappswiggy.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappswiggy.R
import com.example.movieappswiggy.model.MovieResponseModel
import com.example.movieappswiggy.ui.MovieDetailActivity

class MovieAdapter(val context: Context, val mlist: MutableList<MovieResponseModel.MovieItem?>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    val list: MutableList<MovieResponseModel.MovieItem?> = mlist

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title)
        val moviePoster : ImageView = itemView.findViewById(R.id.moviePosterIv)
    }

    fun setMoreData(list: MutableList<MovieResponseModel.MovieItem?>){
        val oldSize = list.size
        this.list.addAll(list)
        notifyItemRangeChanged(oldSize, list.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val item = list.getOrNull(holder.adapterPosition)
            val bundle = Bundle()
            bundle.putString("title", item?.title)
            bundle.putString("poster", item?.poster)
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
            Toast.makeText(parent.context, "Clicked on ${item?.title}", Toast.LENGTH_SHORT).show()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]
        holder.title.text = movie?.title
        Glide.with(context).load(movie?.poster).into(holder.moviePoster)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}