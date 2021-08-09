package com.example.rp_week5

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week5.databinding.ActivityMainBinding
import com.example.rp_week5.models.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


data class Movies(
    var img: Int,
    var name: String,
    var egg_per: String,
    var ratio: String
)

class MainActivity : AppCompatActivity() {

    var MoviesArrayList = ArrayList<Movies>()

    private lateinit var movieAdapter: MovieAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter =MovieAdapter(this, MoviesArrayList)
        binding.movieRv.adapter=movieAdapter


//        getMovieData("3f4eafbaa2f85e95ad46f8feff025dc9", "ko-KR", 1, "KR")




//        binding.movieRv.layoutManager=LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        MoviesArrayList.add(
            Movies(
                R.drawable.movie1,
                "싱크홀",
                "ddd",
                "qqq"
            )
        )

        MoviesArrayList.add(
            Movies(
                R.drawable.movie1,
                "싱크홀",
                "ddd",
                "qqq"
            )
        )
        MoviesArrayList.add(
            Movies(
                R.drawable.movie1,
                "싱크홀",
                "ddd",
                "qqq"
            )
        )
        MoviesArrayList.add(
            Movies(
                R.drawable.movie1,
                "싱크홀",
                "ddd",
                "qqq"
            )
        )








    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }



}