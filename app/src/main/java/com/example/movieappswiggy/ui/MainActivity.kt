package com.example.movieappswiggy.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappswiggy.databinding.ActivityMainBinding
import com.example.movieappswiggy.model.MovieResponseModel
import com.example.movieappswiggy.ui.adapter.MovieAdapter
import com.example.movieappswiggy.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    var adapter : MovieAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullScreen()
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)

        binding.searchEt.requestFocus()
        binding.searchEt.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getSearchedMovie(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        viewModel.data.observe(this) {
            if (!it.isNullOrEmpty()) {
                adapter = MovieAdapter(this, it)
                binding.moviesListRV.adapter = adapter
                binding.moviesListRV.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            }

        }

        viewModel.loaddata.observe(this) {
            if (!it.isNullOrEmpty()) {
                adapter?.setMoreData(it)
            }

        }

        binding.moviesListRV.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as GridLayoutManager
               val tc = manager.itemCount ?: 0
               val vc = manager?.childCount ?: 0
                val vis = manager.findFirstVisibleItemPosition()

                if (vis + vc >= tc){
                    viewModel.getMoreSearchedMovie(binding.searchEt.text.toString())

                }
//                val vit = recyclerView.layoutManager.
//                if (recyclerView.layoutManager?.canScrollVertically() == false){
//                    viewModel.getMoreSearchedMovie(binding.searchEt.text.toString())
//                }

            }

        })

//        val list = mutableListOf<MovieResponseModel.MovieItem?>()
//        list.add(
//            MovieResponseModel.MovieItem(
//            title = "Movie 1",
//            ))
//        list.add(MovieResponseModel.MovieItem(
//            title = "Movie 2",
//        ))
//        list.add(MovieResponseModel.MovieItem(
//            title = "Movie 3",
//        ))
//        val adapter = MovieAdapter(this, list)
//        binding.moviesListRV.adapter = adapter
//        binding.moviesListRV.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)


    }

    fun setFullScreen(){
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.statusBarColor = Color.BLACK
    }
}