package com.ayeshaazeema.nonton.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayeshaazeema.nonton.R
import com.ayeshaazeema.nonton.model.ResultsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()

        fab_detail.setOnClickListener { startActivity(Intent(MainActivity.getLaunchService(this))) }

        val movie = intent.getParcelableExtra<ResultsItem>("EXTRA_MOVIE") as ResultsItem

        Glide.with(this).load(movie.backdropPath).into(iv_backdrop)
        Glide.with(this).load(movie.posterPath).into(iv_poster)
        Glide.with(this).load(movie.video).into(fab_trailer)
        tv_title_detail.text = movie.title.toString()
        tv_release_date_detail.text = movie.releaseDate.toString()
        tv_vote_detail.text = movie.voteAverage.toString()
        tv_overview_detail.text = movie.overview.toString()
        tv_genre_detail.text = movie.genreIds.toString()
    }
}