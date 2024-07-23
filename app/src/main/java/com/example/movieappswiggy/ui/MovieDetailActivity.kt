package com.example.movieappswiggy.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movieappswiggy.R
import com.example.movieappswiggy.databinding.ActivityMainBinding
import com.example.movieappswiggy.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullScreen()

        val extra = intent.extras
        val text = extra?.getString("title", "")
        val poster = extra?.getString("poster", "")

        Glide.with(this).load(poster).into(binding.moviedetailPosterIv)
        binding.titleTV.text = text ?: ""
    }

    fun setFullScreen(){
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.statusBarColor = Color.BLACK
    }
}