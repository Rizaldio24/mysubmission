package com.example.capstone1.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.capstone1.R
import com.example.core.domain.model.Movie
import com.example.capstone1.databinding.ActivityDetailBinding
import com.example.core.utils.DataMapper
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(DATA)
        showDetailMovie(detailMovie)

    }

    private fun showDetailMovie(movieDetail: Movie?){
        movieDetail?.let {
            binding.textViewJudul.text = it.title
            Glide.with(this)
                .load(DataMapper.imageLink + movieDetail.backdropPath)
                .into(binding.imageDetail)
            binding.textViewOverview.text = it.overview
            var favorite = movieDetail.isFavorite
            setFavorite(favorite)

            binding.buttonLike.setOnClickListener{
                favorite = !favorite
                detailViewModel.setFavorite(movieDetail, favorite)
                setFavorite(favorite)
            }
        }
    }

    private fun setFavorite(stateFavorite: Boolean){
        if (stateFavorite){
            binding.buttonLike.text = "Tidak Suka"
        }else{
            binding.buttonLike.text = "Suka"
        }
    }

    companion object{
        val DATA = "data"
    }
}