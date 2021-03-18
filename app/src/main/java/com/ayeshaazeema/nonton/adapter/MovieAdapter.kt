package com.ayeshaazeema.nonton.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayeshaazeema.nonton.R
import com.ayeshaazeema.nonton.activity.DetailActivity
import com.ayeshaazeema.nonton.model.ResultsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*
import org.jetbrains.anko.intentFor

class MovieAdapter(var context: Context, var listMovie: List<ResultsItem?>?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: ResultsItem) {
            with(itemView) {
                tv_title_item.text = movie.title
                tv_release_date_item.text = movie.releaseDate
                tv_vote_item.text = movie.voteAverage.toString()
                tv_overview_item.text = movie.overview
                tv_genre_item.text = movie.genreIds.toString()
                Glide.with(context).load(movie.posterPath).centerCrop().into(iv_item_movie)
                itemView.setOnClickListener {
                    itemView.context.startActivity(itemView.context.intentFor<DetailActivity>("EXTRA_MOVIE" to movie))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovie?.get(position)!!)
    }
}